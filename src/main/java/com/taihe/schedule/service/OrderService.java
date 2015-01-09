package com.taihe.schedule.service;

import java.util.List;

import com.taihe.schedule.entity.OrderInfo;


public interface OrderService {
	
	/** 
	 * 取消未支付的订单
	 * @param activityId 活动ID 
	 */
	public int cancelOrder(String activityId);
	
	/**
	 * 获取超期时间（timeOut）的订单列表</p>count 为需要查询的总数目
	 * 现在订单取消时间范围为timeOut&lt;=cancelTime&lt;=timeOut+定时任务执行时间
	 * @return
	 */
	public List<OrderInfo> getExpiredOrder(long timeOut,int count);
	
	/** 
	 * 设置订单过期
	 * @param orderId
	 * @return 
	 */
	public int setToExpired(String orderId);
	
	/** 
	 * 恢复订单过期状态为待支付
	 * @param orderId
	 * @return 
	 */
	public int cancelExpired(String orderId);
	
	/** 
	 * 根据活动获取所有的已经支付托售订单
	 * @param activityId
	 * @return 
	 */
	public List<OrderInfo> getPaidConsignedOrderByAct(String activityId);
	
	/** 
	 * 比较活动最近一次订单
	 * @param id1
	 * @param id2
	 * @return 如果活动id1最近订单时间，大于id2最近订单时间，返回true，else 返回false
	 */
	public boolean compareLatestOrderByActId(String id1,String id2);
	
	/** 
	 * 获取成功订单的总额
	 * @param actId
	 * @param orderType
	 * @return 
	 */
	public double getNormalOrderAmount(String actId);
}
