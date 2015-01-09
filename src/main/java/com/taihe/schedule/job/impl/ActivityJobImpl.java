package com.taihe.schedule.job.impl;

import com.taihe.schedule.core.support.annotation.ExtendDisallowConcurrent;
import com.taihe.schedule.core.support.annotation.ScheduleAnnotation;
import com.taihe.schedule.entity.*;
import com.taihe.schedule.job.ActivityJob;
import com.taihe.schedule.service.*;
import com.taihe.schedule.util.ActivityType;
import com.taihe.schedule.util.Constants;
import com.taihe.schedule.util.ServiceUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

@ScheduleAnnotation(jobGroup = "activityJob")
@Service
public class ActivityJobImpl implements ActivityJob {
    final static Logger logger = LoggerFactory.getLogger(ActivityJobImpl.class);

    @Autowired
    private FundService fundService;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private StockService stockService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private LogService logService;
    //为了避免和订单过期处理过程冲突，必须延迟一定时间来处理活动结算
    private static final long DELAY_TIME = 45 * 60 * 1000;

    /**
     * 生成活动报表
     */
    @ExtendDisallowConcurrent
    @ScheduleAnnotation
    public void generateActReport() {
        List<Activity> completedActList = activityService.getCompletedAct();//获取已经完成的活动

        if (completedActList == null || completedActList.size() == 0) {
            return;
        }
        logger.debug("准备开始生成报表，共包含已经完成活动数量：" + completedActList.size());
        for (Activity activity : completedActList) {
            try {
                if (!stockService.existedReport(activity.getId())) {
                    generateActReport(activity);
                    logger.debug("成功生成活动报表ActivityId：" + activity.getId());
                } else {
                    logger.debug("已经存在报表，活动为ID:" + activity.getId());
                }
            } catch (Exception e) {
                logger.error("生成活动报表失败，活动ID为：" + activity.getId(), e);
                logService.insertErrorLog(ServiceUtils.getLogInfo(activity.getId(), Constants.BUZITYPE_ACT_REPORT, e.getMessage()));
            }
        }
    }

    //活动名称 ，活动需筹款，是否使用基金 ,活动结束时间------活动信息
    //最终确认收款，原价票数（数量，金额），托售票(数量，金额)，----库存中心
    //基金金额，---------基金信息
    //需支付艺人，需支付场地，------------mongo信息中心
    //票价10%，最终盈利（最终收款-（平台+艺人）），报表时间--------计算结果
    private void generateActReport(Activity activity) {
        ActReport actReport = new ActReport();
        actReport.setActivityid(activity.getId());
        actReport.setActname(activity.getTitle());
        actReport.setActtime(activity.getShowEndDate());
        actReport.setArtrebate(new BigDecimal(activity.getPrices().getPerformer()));//艺人信息佣金
        actReport.setSiterebate(new BigDecimal(activity.getPrices().getSite()));//场地佣金
        actReport.setConsignincome(new BigDecimal(stockService.getConsignsum(activity.getId())));//
        actReport.setConsignsum(stockService.getConsignCount(activity.getId()));//
        actReport.setFinalmoney(new BigDecimal(activity.getHasRaise()));//最终筹款
        actReport.setNeedmoney(new BigDecimal(activity.getPlanRaise()));//计划筹款
        actReport.setTicketamount(stockService.getTicketCount(activity.getId()));
        actReport.setTicketsum(new BigDecimal(orderService.getNormalOrderAmount(activity.getId())));

        if (activity.getFund() != null && activity.getFund().getStatus() == Constants.FUND_AUDIT_STATE_SUC) {
            actReport.setUsefund(1);//是否使用基金
            Fund fund = fundService.getFundInfoById(activity.getFund().getFundId());
            if (fund != null && (!fund.isPlatform()) && fund.getReleaseForm() == Constants.FUND_COMPUTE_STYE_REMAINING) {
                actReport.setFundmoney(new BigDecimal(fund.getAmount()));
            }
        }
        BigDecimal consignedFee = actReport.getConsignincome().multiply(new BigDecimal(1D - 1D / 1.2D));
        actReport.setPlatincome(actReport.getFinalmoney().multiply(new BigDecimal("0.10")));//平台收入
        actReport.setFinalgain(actReport.getFinalmoney().subtract(actReport.getArtrebate()).subtract(actReport.getSiterebate()).add(consignedFee));//最终收入--筹款-（艺人+场地）+托售费用

        stockService.insertActReport(actReport);
        //insert
    }

    @ExtendDisallowConcurrent
    @ScheduleAnnotation
    public void updateToComplete() {//更新演出时间结束的活动
        List<Activity> activities = activityService.getShowingActivities(DELAY_TIME);
        for (Activity activity : activities) {
            try {
                process(activity);
                logger.debug("演出结束活动：" + activity.getId());
            } catch (Exception e) {
                logger.error("活动处理失败，活动ID为：" + activity.getId(), e);
                logService.insertErrorLog(ServiceUtils.getLogInfo(activity.getId(), Constants.BUZITYPE_ACTIVITY_CLEAN, e.getMessage()));
            }
        }
    }

    private void process(Activity activity) {
        String id = activity.getId();
        //更新库存和票表表数据
        stockService.cancelMidStock(id);
        //清空库存
        stockService.cleanStock(id);
        //清空托售
        stockService.cleanConsignedNum(id);
        //所有托售未支付变为取消
        stockService.cancelConsignedticketsByActivity(id);
        //更新票的状态
        stockService.updateTicketsExpired(id);
        //取消所有未完成订单
        orderService.cancelOrder(id);
        //结算 已经完成的活动
        balance(id);
        //更新活动状态为已完成
        activityService.updateActivitiesCompleted(activity);
    }

    /**
     * 结算任务，看看需要给谁退钱---
     *
     * @param activyId
     */
    private void balance(String activyId) {
        boolean isMatch = true;
        Map<Integer, BigDecimal> payback = new HashMap<>();
        List<OrderInfo> orderInfos = orderService.getPaidConsignedOrderByAct(activyId);
        for (OrderInfo orderInfo : orderInfos) {
            List<ConsignedTicket> consignedTickets = stockService.getPaidConsignedTicketsByOrder(orderInfo.getOrderId());


            //既然现在没管单张订单，就先不使用该逻辑
            /*if(consignedTickets.size()!=orderInfo.getTotalNum()){
                //插入信息不一致记录
				logger.debug("订单托售票数与托售记录数据不一致OrderId="+orderInfo.getOrderId());
				isMatch = false;
				continue;
			}*/

            BigDecimal ticketsPrice = new BigDecimal("0");
            for (ConsignedTicket consignedTicket : consignedTickets) {
                BigDecimal singlePrice = consignedTicket.getPrice() == null ? new BigDecimal("0") : consignedTicket.getPrice();
                ticketsPrice = ticketsPrice.add(singlePrice);
                if (payback.containsKey(consignedTicket.getFromuser())) {
                    payback.put(consignedTicket.getFromuser(), payback.get(consignedTicket.getFromuser()).add(singlePrice));
                } else {
                    payback.put(consignedTicket.getFromuser(), singlePrice);
                }
            }
            if (!ticketsPrice.equals(orderInfo.getTotalAmount())) {
                //插入信息不对称记录
                logger.error("订单支付总额，与托售票票总金额不符ORDERID=" + orderInfo.getOrderId());
                isMatch = false;
                continue;
            }
        }

        //根据isMatch整体对比失败或者成功，插入记录
        for (Iterator<Entry<Integer, BigDecimal>> iterator = payback.entrySet().iterator(); iterator.hasNext(); ) {
            Entry<Integer, BigDecimal> enty = iterator.next();
            int usrId = enty.getKey();
            BigDecimal bigDecimal = enty.getValue();
            Balance balance = getBalance(usrId, activyId, isMatch == true ? 1 : 0, bigDecimal);
            stockService.insertBalanceInfo(balance);
        }
    }

    /**
     * @param userId
     * @param activityId
     * @param type          类型。0为结账失败，1为结账成功
     * @param backOffAmount 需要退回的钱的数量
     * @return
     */
    private Balance getBalance(int userId, String activityId, int type, BigDecimal backOffAmount) {
        Balance balance = new Balance();
        balance.setActivityid(activityId);
        balance.setConsigedamount(backOffAmount);
        balance.setType(type);
        balance.setUserid(userId);
        return balance;
    }

    @Override
    @ExtendDisallowConcurrent
    @ScheduleAnnotation
    public void cleanRaisingAct() {//处理type=1和2 筹款问题  ==》 将level=2变为 4或8
        List<Activity> activities = activityService.getPreparationExpired();
        for (Activity activity : activities) {
            int activityType = activity.getType();
            if (activityType == ActivityType.NORMAL || activityType == ActivityType.TOUR) {
                if ((activity.getHasRaise() - activity.getPlanRaise()) >= 0) {
                    activityService.updateActStatus(activity.getId(), Constants.COLLECT_MONEY_FINISHED);
                } else {
                    activityService.updateActStatus(activity.getId(), Constants.FAILED);
                }
            } 
        }
    }
    
    @ExtendDisallowConcurrent
    @ScheduleAnnotation
	public void switchToSellOver() {//处理type=3筹款问题  ==》 将level=2变为 10或11
    	List<Activity> activites=activityService.getActivityExpiredByRelease();
    	for (Activity activity : activites) {
    		String activityId=activity.getId();
    		int dealType=activity.getDealType();
			if(dealType==Constants.STOP_SELL||dealType==Constants.OLD_SELL){
				activityService.updateActStatus(activityId, Constants.SELL_OVER);
			}else if(dealType==Constants.COST_SELL){
				ActivityPrice price=activity.getPrices();
				price.setActivity(activity.getCostPrice());
				activityService.updateActStatusAndCost(activityId, Constants.SELL_COST, price);
			}
		}
	}

    //处理演出时间
    @ExtendDisallowConcurrent
    @ScheduleAnnotation
    public void switchToShowing() {//type=1和2和3 --> 演出中
        activityService.updateToShowing();
    }
    
    @ExtendDisallowConcurrent
    @ScheduleAnnotation
	public void switchTrailerToShowing() {//type=4 --> 演出中
    	activityService.updateTrailerToShowing();
	}  
}
