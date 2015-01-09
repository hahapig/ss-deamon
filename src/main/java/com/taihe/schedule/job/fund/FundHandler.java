package com.taihe.schedule.job.fund;

import com.taihe.schedule.job.fund.support.ActivityFundWrapper;


public interface FundHandler {
	
	public boolean matchType(ActivityFundWrapper fundWrapper);
	
	
	/** 
	 * 
	 * @param fundWrapper 参数代表所有的活动都在一个基金下面
	 */
	public void handle(ActivityFundWrapper fundWrapper);
}
