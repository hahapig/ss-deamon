package com.taihe.schedule.job;


public interface InfoJob {
	
	/** 
	 * 给场地和艺人定时增加日程----开放180天之后的预约
	 */
	public void generateCalenderForSiteAndPerformer();
	
	/** 
	 *  删除过期的日程
	 */
	public void deleteExpiredCalendarEvent();
	
}
