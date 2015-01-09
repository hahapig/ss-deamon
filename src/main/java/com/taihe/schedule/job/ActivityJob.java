package com.taihe.schedule.job;


public interface ActivityJob {
	
	public void updateToComplete();
	
	public void generateActReport();
	
	/** 
	 *更新那些活动状态为筹资中的活动,目前只修改活动状态
	 *备注：处理 活动类型type=1、2  level=2
	 */
	public void cleanRaisingAct();
	
	/** 
	 *  把那些到演出时间的更新为演出中
	 */
	public void switchToShowing();
	/** 
	 *  把那些到演出时间的更新为演出中
	 */
	public void switchTrailerToShowing();
	
	/** 
	 *  把活动type为3的根据是否停止售票更新到指定状态
	 */
	public void switchToSellOver();
}
