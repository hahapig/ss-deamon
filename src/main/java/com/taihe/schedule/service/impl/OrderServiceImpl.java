package com.taihe.schedule.service.impl;

import com.taihe.schedule.core.dynamic.DataSource;
import com.taihe.schedule.core.dynamic.DataSourceConst;
import com.taihe.schedule.core.persist.Persistence;
import com.taihe.schedule.entity.OrderInfo;
import com.taihe.schedule.service.OrderService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@DataSource(DataSourceConst.MYSQL_ORDER_DB)
@Service

public class OrderServiceImpl implements OrderService {

	final static Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

	private static final String NAMESPACE = "orderService.";

	@Autowired
	private Persistence persistence;

	@Override
	public int cancelOrder(String activityId){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("activityId", activityId);
		return persistence.update(NAMESPACE + "cancelOrder", params);
	}

	@Override
	public List<OrderInfo> getExpiredOrder(long timeOut, int count){
		Map<String, Object> params = new HashMap<String, Object>();
		Date now = new Date();
		long mills = now.getTime() - timeOut;
		params.put("date", new Date(mills));
		params.put("count", count);
		return persistence.selectList(NAMESPACE + "getExpiredOrder", params, OrderInfo.class);
	}

	public int setToExpired(String orderId){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("orderId", orderId);
		try {
			return persistence.update(NAMESPACE + "setToExpired", params);
		} catch (Exception ex) {
			logger.error("exec order cancel execption,order:" + orderId, ex);
			return 0;
		}
	}

	@Override
	public List<OrderInfo> getPaidConsignedOrderByAct(String activityId){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("activityId", activityId);
		return persistence.selectList(NAMESPACE + "getPaidConsignedOrderByAct", params, OrderInfo.class);
	}

	@Override
	public boolean compareLatestOrderByActId(String id1, String id2){
		Map<String, String> params = new HashMap<String, String>();
		params.put("actId", id1);
		OrderInfo info1 = persistence.selectObject(NAMESPACE + "getLatestOrderByAct", params, OrderInfo.class);
		if (info1 == null) {
			return false;
		}
		params.put("actId", id2);
		OrderInfo info2 = persistence.selectObject(NAMESPACE + "getLatestOrderByAct", params, OrderInfo.class);
		if (info2 == null) {
			return true;
		}
		return (info1.getVdate().getTime() - info2.getVdate().getTime()) > 0;
	}

	@Override
	public double getNormalOrderAmount(String actId){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("activityId", actId);
		return persistence.selectObject(NAMESPACE + "getNormalOrderAmount", params, double.class);
	}

	@Override
	public int cancelExpired(String orderId){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("orderId", orderId);
		try {
			return persistence.update(NAMESPACE + "cancelExpired", params);
		} catch (Exception ex) {
			logger.error("exec order cancel execption,order:" + orderId, ex);
			return 0;
		}
	}
}
