package com.taihe.schedule.service;

import java.util.List;

import com.taihe.schedule.entity.Activity;
import com.taihe.schedule.entity.ActivityPrice;


public interface ActivityService {
	
	/** 
	 * 获取已经结束的活动(状态为演出中，时间已经超期)
	 * 备注：type=1、2、3、4 level=5 
	 * @return 
	 */
	public List<Activity> getShowingActivities(long delayTime);
	
	/** 
	 * 更新活动状态为已结束
	 * @param activities
	 * @return 
	 */
	public int updateActivitiesCompleted(List<Activity> activities);
	
	/** 
	 * 更新活动状态为已结束
	 * @param activity
	 * @return 
	 */
	public int updateActivitiesCompleted(Activity activity);
	
	/** 
	 * 获取正在筹资的活动
	 * @return 
	 */
	public List<Activity> getAllCollectingAct();
	
	/**
	 * 获取状态为已完成的活动  level=6
	 * */
	public List<Activity> getCompletedAct();
	
	/** 
	 * 获取已过最短筹备期 状态为筹款中(2)的活动  
	 * 备注：专门处理 活动类型type=1 和 2
	 * @return 
	 */
	public List<Activity> getPreparationExpired();
	
	/**
	 * 更新获取所有type为3，已过筹备期的活动
	 * @return
	 * */
	public List<Activity> getActivityExpiredByRelease();
	
	/** 
	 * 更新type=1、2所有符合条件活动为演出中
	 * @return 
	 */
	public int updateToShowing();
	
	/** 
	 * 更新type=4所有符合条件活动为演出中
	 * @return 
	 */
	public int updateTrailerToShowing();
	
	/**
	 * 更新level为11,原价为cost
	 * */
	public int updateActStatusAndCost(String actId,int status, ActivityPrice price);
	
	/**
	 * 更新活动状态的方法
	 * @return
	 * */
	public int updateActStatus(String actId,int status);
	public int updateActivityStatusById(List<Activity> activities);
	
	//测试
	public List<Activity> getActivityExpiredTest();
}
