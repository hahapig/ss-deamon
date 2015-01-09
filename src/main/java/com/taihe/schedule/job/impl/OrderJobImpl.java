package com.taihe.schedule.job.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taihe.schedule.core.support.annotation.ExtendDisallowConcurrent;
import com.taihe.schedule.core.support.annotation.ScheduleAnnotation;
import com.taihe.schedule.entity.OrderInfo;
import com.taihe.schedule.job.OrderJob;
import com.taihe.schedule.service.LogService;
import com.taihe.schedule.service.OrderService;
import com.taihe.schedule.service.StockService;
import com.taihe.schedule.util.Constants;
import com.taihe.schedule.util.ServiceUtils;

@ScheduleAnnotation(jobGroup = "orderJob")
@Service
public class OrderJobImpl implements OrderJob {

	// 处理的订单数量
	private static final int PROCESS_COUNT = 200;

	// 默认15分钟--测试2分钟
	private static final long TIMEOUT = 15*60*1000;

	final static Logger logger = LoggerFactory.getLogger(OrderJobImpl.class);

	@Autowired
	private OrderService orderService;

	@Autowired
	private StockService stockService;

	@Autowired
	private LogService logService;

	@ExtendDisallowConcurrent
	@ScheduleAnnotation()
	public void cleanExpiredOrder(){
		List<OrderInfo> orders = orderService.getExpiredOrder(TIMEOUT, PROCESS_COUNT);
		// 一共获取到过期订单
		for (OrderInfo orderInfo : orders) {
			logger.info("************step1:"+orderInfo.getMid_stockid());
			String orderId = null;
			try {
				orderId = resolveOrderId(orderInfo);
				orderService.setToExpired(orderId);//设置订单过期
				//if(orderService.setToExpired(orderId)>0){
					logger.debug("开始处理订单,ORDERID=" + orderId);
					process(orderInfo, orderId);
				//}
			} catch (Exception e) {
				logService.insertErrorLog(ServiceUtils.getLogInfo(orderId, Constants.BUZITYPE_ORDER_EXPIRED, "orderId:"
						+ orderInfo.getOrderId() + "[错误信息:]" + e.getMessage()));
				logger.error("订单处理失败", e);
			}
		}
	}

	private void process(OrderInfo object, String orderId){
		int type = resolveOrderType(object);
		boolean isCancelOrder = true;// 是否取消订单
		if (type == 2 || type == 3) {// 托售订单，恢复已锁定票数目
			String activityId = resolveActivityId(object);
			int num = resolveOrderNum(object);
			//事务取消
			try{
				isCancelOrder=stockService.transcationCancelStock(activityId, num, orderId)>0;
			}catch(Exception ex){
				isCancelOrder = false;
				logger.error("取消库存事务回滚,orderInfo:"+orderId,ex);
			}
			/*if(stockService.addConsignedNum(activityId, num)>0)
			{
				// 更新所有的托售为 取消
				stockService.cancelConsignedticketsByOrder(orderId);
			}*/
			
		} else if (type == 1) {// 普通订单清除票的中间记录
			String midStockId = resolveMidStockId(object);
			logger.info("*********************midStockId:"+midStockId);
			int effect = stockService.cancelMidStockByMidStockId(midStockId);
			if (effect == 0) {
				isCancelOrder = false;
			}
		} else {
			logger.error("处理过期订单遇到无法处理的订单，因为订单类型未知，订单类型为：" + type);
			logService.insertErrorLog(ServiceUtils.getLogInfo(orderId, Constants.BUZITYPE_ORDER_EXPIRED,
					"处理过期订单遇到无法处理的订单，因为订单类型未知，订单类型为：" + type));
		}
		if (!isCancelOrder) {
			logger.error("订单取消失败，恢复订单状态orderID="+orderId);
			orderService.cancelExpired(orderId);
		}
	}

	private String resolveOrderId(OrderInfo orderObject){
		return String.valueOf(orderObject.getOrderId());
	}

	private String resolveActivityId(OrderInfo orderObject){
		return orderObject.getActivity_id();
	}

	private String resolveMidStockId(OrderInfo orderObject){
		return String.valueOf(orderObject.getMid_stockid());
	}

	private int resolveOrderType(OrderInfo orderObject){
		return orderObject.getOrdertype();
	}

	private int resolveOrderNum(OrderInfo orderObject){
		return orderObject.getNum();
	}
}
