package com.taihe.schedule.job;

/**
 * 库存中心定时任务
 * */
public interface StockJob {
	/**
	 * 清理过期商品
	 * */
	public void cleanExpiredGoods();
	
	/**
	 * 定时更新验票结果
	 * */
	public void checkedTickets();
}
