package com.taihe.schedule.service;

import java.util.List;

import com.taihe.schedule.entity.ActReport;
import com.taihe.schedule.entity.Balance;
import com.taihe.schedule.entity.ConsignedTicket;
import com.taihe.schedule.entity.Goods;
import com.taihe.schedule.entity.TicketCheked;


public interface StockService {
	
	/** 
	 * 清除平台的库存
	 * @param activityId 
	 */
	public void cleanStock(String activityId);
	
	/** 
	 * 更新库存中间表未支付记录为取消
	 * @param activityId 
	 */
	public void cancelMidStock(String activityId);
	/**
	 * transcation cancle stockId
	 * 
	 * @param midStockId 
	 * @param orderId
	 * @return
	 */
	 int transcationCancelStock(String activityId, int num,String orderId);
	/** 
	 * 更新票信息为过期
	 * @param activity 
	 */
	public void updateTicketsExpired(String activity);
	
	/** 
	 * 清除托售票库存
	 * @param activity 
	 */
	public void cleanConsignedNum(String activity);
	
	/** 
	 * 增加托售票库存
	 * @param activityId 
	 */
	public int addConsignedNum(String activityId,int num);
	
	/** 
	 * 通过midstockId取消已消费的库存
	 * @param midStockId 
	 */
	public int cancelMidStockByMidStockId(String midStockId);
	
	/** 
	 * 根据订单ID取消托售票
	 * @param orderId
	 * @return 
	 */
	public int cancelConsignedticketsByOrder(String orderId);
	
	/** 
	 * 根据活动ID取消，所有未支付的托售票
	 * @param activityId
	 * @return 
	 */
	public int cancelConsignedticketsByActivity(String activityId);
	
	/** 
	 * 通过订单，获取所有已经支付的托售票
	 * @param orderId
	 * @return 
	 */
	public List<ConsignedTicket> getPaidConsignedTicketsByOrder(int orderId);
	
	/** 
	 * 插入结算信息
	 * @param balance
	 * @return 
	 */
	public int insertBalanceInfo(Balance balance);
	
	/** 
	 * 根据活动ID获取已售普通票的总数
	 * @param actId
	 * @return 
	 */
	public int getTicketCount(String actId);
	
	
	/** 
	 * 根据活动ID获取已售托售票的总金额
	 * @param actId
	 * @return 
	 */
	public double getConsignsum(String actId);
	
	/** 
	 * 根据活动ID获取已售托售票的总数量
	 * @param actId
	 * @return 
	 */
	public int getConsignCount(String actId);
	
	/** 
	 * 插入报表信息
	 * @param actReport
	 * @return 
	 */
	public int insertActReport(ActReport actReport); 
	
	/** 
	 * 检查是否存在报表
	 * @param actid
	 * @return 
	 */
	public boolean existedReport(String actid);
	
	/**
	 * 检查库存中心商品是否过期
	 * */
	public List<Goods> getExpiredGoods();
	
	/**
	 * 更新库存中心商品
	 * */
	public int updateExpiredGoods(List<Goods> goods);
	
	/**
	 * 更新验票文档
	 * */
	public int checkedTickets();
}
