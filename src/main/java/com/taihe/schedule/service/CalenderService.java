package com.taihe.schedule.service;


public interface CalenderService {
	
	/** 
	 * 插入新的日程
	 * @param uId
	 * @param userType
	 * @param days 距离当天，需要推迟的天数
	 */
	public void insertCalender(String uId,int userType,int days);
	
	/** 
	 *  删除过期的日程
	 */
	public void deleteExpiredCalendarEvent();
	
	/** 
	 *  删除报批场地38天的日程
	 */
	public void deleteApprovaledCalendarInfo(String uid,int period,int userType);
}
