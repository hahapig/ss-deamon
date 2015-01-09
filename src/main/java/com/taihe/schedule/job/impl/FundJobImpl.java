package com.taihe.schedule.job.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taihe.schedule.core.support.annotation.ExtendDisallowConcurrent;
import com.taihe.schedule.core.support.annotation.ScheduleAnnotation;
import com.taihe.schedule.entity.Activity;
import com.taihe.schedule.entity.Fund;
import com.taihe.schedule.job.FundJob;
import com.taihe.schedule.job.fund.FundHandler;
import com.taihe.schedule.job.fund.support.ActivityFundWrapper;
import com.taihe.schedule.service.ActivityService;
import com.taihe.schedule.service.FundService;
import com.taihe.schedule.util.Constants;

@ScheduleAnnotation(jobGroup="fundJob")
@Service
public class FundJobImpl implements FundJob{
	
	final static Logger logger = LoggerFactory.getLogger(FundJobImpl.class);
	@Autowired
	private FundService fundService;
	
	@Autowired
	private ActivityService activityService;
	
	@Autowired
	private List<FundHandler> fundHandlers;
	
	@ExtendDisallowConcurrent
	@ScheduleAnnotation
	public void handleActivityAccordFund(){
		List<Activity> list = activityService.getAllCollectingAct();
		if(list==null||list.size()<=0){
			return;
		}
		//其实这一步，是按照基金分类
		Map<String, ActivityFundWrapper> map = new HashMap<String, ActivityFundWrapper>();
		for (Activity activity : list) {
			if(activity.getFund()==null||activity.getFund().getStatus()!=Constants.FUND_AUDIT_STATE_SUC){
				continue;
			}
			
			if(map.containsKey(activity.getFund().getFundId())){
				map.get(activity.getFund().getFundId()).getActivities().add(activity);
			}else{
				Fund fund = fundService.getFundInfoById(activity.getFund().getFundId());
				if(fund==null){
					throw new RuntimeException("查找基金信息失败，可能不存在基金。ID="+activity.getFund().getFundId());
				}
				
				if(fund.isPlatform()){
					logger.debug("不处理平台基金");
					continue;
				}
				ActivityFundWrapper activityFundWrapper = new ActivityFundWrapper();
				activityFundWrapper.getActivities().add(activity);
				activityFundWrapper.setFund(fund);
				map.put(activity.getFund().getFundId(), activityFundWrapper);
			}
		}
		
		Set<String> keys = map.keySet();
		for (String string : keys) {
			FundHandler handler = getHandler(map.get(string));
			if(handler==null){
				logger.error("无法处理与基金相关的活动，基金的key为"+string);
				continue;
			}
			handler.handle(map.get(string));
		}
	}
	
	private FundHandler getHandler(ActivityFundWrapper activityFundWrapper){
		final List<FundHandler> fundHandlers = this.fundHandlers;
		for (FundHandler fundHandler : fundHandlers) {
			if(fundHandler.matchType(activityFundWrapper)){
				return fundHandler;
			}
		}
		return null;
	}
}
