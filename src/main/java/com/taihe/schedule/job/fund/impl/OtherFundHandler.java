package com.taihe.schedule.job.fund.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taihe.schedule.core.persist.MogonPersistence;
import com.taihe.schedule.entity.Activity;
import com.taihe.schedule.entity.Fund;
import com.taihe.schedule.job.fund.FundHandler;
import com.taihe.schedule.job.fund.support.ActivityFundWrapper;
import com.taihe.schedule.job.fund.support.GroupedActivity;
import com.taihe.schedule.service.ActivityService;
import com.taihe.schedule.service.MsgSendService;
import com.taihe.schedule.service.OrderService;
import com.taihe.schedule.util.Constants;

@Service
public class OtherFundHandler implements FundHandler{
	
	@Autowired
	private MogonPersistence mogonPersistence;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private ActivityService activityService;
	
	@Autowired
	private MsgSendService msgSendService;
	
	@Override
	public void handle(ActivityFundWrapper fundWrapper){
		List<Activity> activities = fundWrapper.getActivities();
		
		//按照场地+时间(单位天)细分<site+date,activityList>
		Map<String, List<GroupedActivity>> siteTimeRefActivity = new HashMap<String, List<GroupedActivity>>();
		for (Activity activity : activities) {
			GroupedActivity groupedActivity = new GroupedActivity(activity);
			if(siteTimeRefActivity.get(groupedActivity.getGroupKey())==null){
				List<GroupedActivity> actList = new ArrayList<GroupedActivity>();
				actList.add(groupedActivity);
				siteTimeRefActivity.put(groupedActivity.getGroupKey(), actList);
			}else{
				siteTimeRefActivity.get(groupedActivity.getGroupKey()).add(groupedActivity);
			}
		}
		//处理细分后的活动
		Set<String> keys = siteTimeRefActivity.keySet();
		for (String string : keys) {
			List<GroupedActivity> groupedAct = siteTimeRefActivity.get(string);
			//按照进度排序
			Collections.sort(groupedAct,new ActivityComparator(fundWrapper.getFund(),orderService));
			//排名靠前的可能为最优解
			changeGroupedStatus(groupedAct, fundWrapper.getFund());
			//根据处理结果更新活动状态
			updateActivities(groupedAct);
		}
	}
	
	/** 
	 * 找出最高进度，然后，找出冲突的活动，并取消，
	 * @param activities 列表所有的活动来自同一天，同一演出地点，同一基金，列表最靠前的为进度最高
	 */
	private void changeGroupedStatus(List<GroupedActivity> activities,Fund fund){
		GroupedActivity candidate = activities.get(0);
		if(activities.size()==1){//当只有一个元素，需要单独计算进度
			double rate1 = 0D;
			Activity activity1 = candidate.getActivity();
			//百分结算制
			if(fund.getReleaseForm()==Constants.FUND_COMPUTE_STYLE_RATE){
				rate1 = activity1.getHasRaise()/activity1.getPlanRaise();
			}
			
			//剩余份额终结制
			if(fund.getReleaseForm()==Constants.FUND_COMPUTE_STYE_REMAINING){
				rate1 = (activity1.getHasRaise()+fund.getAmount())/activity1.getPlanRaise();
			}
			activities.get(0).setRate(rate1);
		}
		
		boolean reached = reachTheEnd(candidate, fund);
		candidate.getActivity().setLevel(Constants.COLLECT_MONEY_FINISHED);
		candidate.setStatus(Constants.COLLECT_MONEY_FINISHED);
		if(candidate.getRate()==0){//如果进度为0，并且超过最后筹款期，取消所有
			if(reached){//并且超过最后筹款期，取消所有
				cancelAll(activities);
			}else{//没超过最后筹款期
				//do nothing
			}
		}
		
		if(candidate.getRate()>=0){//如果进度大于0
			if(fund.getReleaseForm()==Constants.FUND_COMPUTE_STYLE_RATE){
				//检测冲突，并且取消冲突的项目
				checkConflict(activities, candidate);
			}
			if(fund.getReleaseForm()==Constants.FUND_COMPUTE_STYE_REMAINING){
				if(candidate.getRate()<=0.99D&&reached){//如果没有达到100%，并且已经达到最终筹款期限
					cancelAll(activities);
				}else {
					//检测冲突，取消冲突项目
					checkConflict(activities, candidate);
				}
			}
		}
	}
	
	private void checkConflict(List<GroupedActivity> activities,GroupedActivity candidate){
		if(activities.size()<=1){
			return;
		}
		
		long candidateStart = candidate.getActivity().getShowStartDate().getTime();
		long candidateEnd = candidate.getActivity().getShowEndDate().getTime();
		
		//注意不是从第一个值开始
		for (int i = 1; i < activities.size(); i++) {
			GroupedActivity target = activities.get(i);
			long targetStart = target.getActivity().getShowStartDate().getTime();
			long targetEnd = target.getActivity().getShowEndDate().getTime();
			if((targetEnd >= candidateStart) && (targetStart <= candidateEnd)){
				target.getActivity().setLevel(Constants.FAILED);
				target.setStatus(Constants.FAILED);
             }
		}
	}
	
	private void cancelAll(List<GroupedActivity> activities){
		for (GroupedActivity groupedActivity : activities) {
			groupedActivity.setStatus(Constants.FAILED);
			groupedActivity.getActivity().setLevel(Constants.FAILED);
		}
	}
	
	/** 
	 * 是否已经到最短筹备期
	 * @param candidate
	 * @param fund
	 * @return 
	 */
	private boolean reachTheEnd(GroupedActivity candidate,Fund fund){
		long shortestTime = 0L;//
		if(fund.getReleaseForm()==Constants.FUND_COMPUTE_STYLE_RATE){
			shortestTime = candidate.getActivity().getPreparation().getTime() - (long)fund.getPreBalanceDay()*24*60*60*1000;
		}
		
		if(fund.getReleaseForm()==Constants.FUND_COMPUTE_STYE_REMAINING){
			shortestTime = candidate.getActivity().getPreparation().getTime();
		}
		
		if(shortestTime==0L){
			throw new RuntimeException("基金发放类型为非法参数fundId="+fund.getId());
		}
		Date now = new Date();
		
		return now.getTime() > shortestTime;
	}
	
	/** 
	 * 根据GroupedActivity中的status字段处理结果
	 * @param GroupedActivity 
	 */
	private void updateActivities(List<GroupedActivity> activities){
		List<Activity> list = new ArrayList<>();
		StringBuilder msg = new StringBuilder();
		for (GroupedActivity groupedActivity : activities) {
			list.add(groupedActivity.getActivity());
			if(groupedActivity.getStatus()==Constants.FAILED){
				msg.append("活动[ID:"+groupedActivity.getActivity().getId()+",名称："+groupedActivity.getActivity().getTitle()+"]");
			}
		}
		
		activityService.updateActivityStatusById(list);
		if(msg.length()>0){
			msgSendService.sendEmailForCancelAct("被取消的活动有："+msg.toString());
		}
		
	}
	
	//尼玛，只管进度排序
	static class ActivityComparator implements Comparator<GroupedActivity>{
		private Fund fund;
		private OrderService orderService;
		public ActivityComparator(Fund fund,OrderService orderService){
			this.fund = fund;
			this.orderService = orderService;
		}

		@Override
		public int compare(GroupedActivity o1, GroupedActivity o2){
			Activity activity1 = o1.getActivity();
			Activity activity2 = o2.getActivity();
			
			double rate1 = 0D,rate2 = 0D;
			//百分结算制
			if(fund.getReleaseForm()==Constants.FUND_COMPUTE_STYLE_RATE){
				rate1 = activity1.getHasRaise()/activity1.getPlanRaise();
				rate2 = activity2.getHasRaise()/activity2.getPlanRaise();
			}
			
			//剩余份额终结制
			if(fund.getReleaseForm()==Constants.FUND_COMPUTE_STYE_REMAINING){
				rate1 = (activity1.getHasRaise()+fund.getAmount())/activity1.getPlanRaise();
				rate2 = (activity2.getHasRaise()+fund.getAmount())/activity2.getPlanRaise();
			}
			
			o1.setRate(rate1);
			o2.setRate(rate2);
			
			if((rate1==0)&&(rate2==0)){
				return 0;
			}
			
			//进度相等，则比较订单,订单较新，则排后面
			if(o1.getRate()==o2.getRate()){
				//o1的订单
				 if(orderService.compareLatestOrderByActId(o1.getActivity().getId(), o2.getActivity().getId())){
					 rate1 = rate1-1000D;
				 }
			}
			return rate1>rate2?-1:1;
		}}

	@Override
	public boolean matchType(ActivityFundWrapper fundWrapper){
		if(fundWrapper.getFund()!=null&&(!fundWrapper.getFund().isPlatform())){
			return true;
		}
		return false;
	}
}
