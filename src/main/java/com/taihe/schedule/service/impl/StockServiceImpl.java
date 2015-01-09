package com.taihe.schedule.service.impl;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.taihe.schedule.core.dynamic.DataSource;
import com.taihe.schedule.core.dynamic.DataSourceConst;
import com.taihe.schedule.core.persist.Persistence;
import com.taihe.schedule.entity.ActReport;
import com.taihe.schedule.entity.Balance;
import com.taihe.schedule.entity.ConsignedTicket;
import com.taihe.schedule.entity.Goods;
import com.taihe.schedule.entity.TicketCheked;
import com.taihe.schedule.service.StockService;
import com.taihe.schedule.util.Constants;
import com.taihe.schedule.util.HttpUtils;
import com.taihe.schedule.util.StreamUtil;

@DataSource(DataSourceConst.MYSQL_STOCK_DB)
@Service
public class StockServiceImpl implements StockService {

	final static Logger logger = LoggerFactory.getLogger(StockServiceImpl.class);

	private static final String NAMESPACE = "stockService.";

	@Autowired
	private Persistence persistence;

	@Override
	public void cleanStock(String activityId){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("activityId", activityId);
		persistence.update(NAMESPACE + "cleanStock", params);
	}

	@Override
	public void cancelMidStock(String activityId){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("activityId", activityId);
		persistence.update(NAMESPACE + "cancelMidStock", params);
	}

	@Override
	public void updateTicketsExpired(String activity){
		logger.debug("票 暂无过期状态标示，不处理");
		// do nothing
		/*
		 * Map<String, Object> params = new HashMap<String, Object>(); params.put("activityId", activity);
		 * persistence.update(NAMESPACE+"updateTicketsExpired", params);
		 */
	}

	@Override
	public void cleanConsignedNum(String activity){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("activityId", activity);
		persistence.update(NAMESPACE + "cleanConsignedNum", params);
	}

	@Override
	public int addConsignedNum(String activityId, int num){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("activityId", activityId);
		params.put("num", num);
		try{
			return persistence.update(NAMESPACE + "addConsignedNum", params);
		}catch(Exception ex){
			logger.error("cancel order execption,activityId by :"+activityId,ex);
			return 0;
		}
	}

	@Override
	@Transactional
	public int cancelMidStockByMidStockId(String midStockId){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("midStockId", midStockId);
		try {
			int effect = persistence.update(NAMESPACE + "cancelMidStockByMidStockId", params);
			if (effect == 1) {
				logger.info("cancle stockId success ,stockId:" + midStockId);
			} else {
				logger.error("cancle stockId fail ,stockId:" + midStockId);
			}
			return effect;
		} catch (Exception ex) {
			logger.error("cancle stockId exception ,stockId:" + midStockId+",ex:",ex);
		}
		return 0;
	}
	/**
	 * transcation cancle stockId
	 * 
	 * @param midStockId
	 * @param orderId
	 * @return
	 */
	@Transactional
	public int transcationCancelStock(String activityId, int num,String orderId){
		if(addConsignedNum(activityId,num)>0){
			/*if(cancelConsignedticketsByOrder(orderId)>0){
				return 1;
			}*/ //强哥说不需要这一步操作
			return 1;
		}
		throw new RuntimeException("transcation cancle by rollback");
	}
	@Override
	public int cancelConsignedticketsByOrder(String orderId){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("orderId", orderId);
		try {
			int effect = persistence.update(NAMESPACE + "cancelConsignedticketsByOrder", params);
			if (effect == 1) {
				logger.info("method:cancelConsignedticketsByOrder.msg:cancle stockId success ,orderId:" + orderId);
			} else {
				logger.error("method:cancelConsignedticketsByOrder.cancle msg:cancle stockId fail ,orderId:" + orderId);
			}
			return effect;
		} catch (Exception ex) {
			logger.error("method:cancelConsignedticketsByOrder.cancle msg:cancle stockId exception ,stockId:" + orderId+",ex:",ex);
		}
		return 0;
	}

	@Override
	public int cancelConsignedticketsByActivity(String activityId){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("activityId", activityId);
		return persistence.update(NAMESPACE + "cancelConsignedticketsByActivity", params);
	}

	@Override
	public List<ConsignedTicket> getPaidConsignedTicketsByOrder(int orderId){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("orderId", orderId);
		return persistence.selectList(NAMESPACE + "getPaidConsignedTicketsByOrder", params, ConsignedTicket.class);
	}

	@Override
	public int insertBalanceInfo(Balance balance){
		return persistence.insert(NAMESPACE + "insertBalanceInfo", balance);
	}

	@Override
	public int getTicketCount(String actId){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("activityId", actId);
		return persistence.selectObject(NAMESPACE + "getTicketCount", params, Integer.class);
	}

	@Override
	public double getConsignsum(String actId){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("activityId", actId);
		return persistence.selectObject(NAMESPACE + "getConsignsum", params, double.class);
	}

	@Override
	public int getConsignCount(String actId){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("activityId", actId);
		return persistence.selectObject(NAMESPACE + "getConsignCount", params, Integer.class);
	}

	@Override
	public int insertActReport(ActReport actReport){
		return persistence.insert(NAMESPACE + "insertActReport", actReport);
	}

	@Override
	public boolean existedReport(String actid){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("activityId", actid);
		int record = persistence.selectObject(NAMESPACE + "existedReport", params, Integer.class);
		return record >= 1;
	}

	/**
	 * 获取过期的商品们
	 * */
	@Override
	public List<Goods> getExpiredGoods() {
		return persistence.selectList(NAMESPACE + "getExpiredGoods",null,Goods.class);
	}

	@Override
	public int updateExpiredGoods(List<Goods> goods) {
		return persistence.bacthUpdate(NAMESPACE + "updateExpiredGoods",goods);
	}

	/**
	 * 读取验票文档
	 * */
	@Override
	public int checkedTickets() {
		logger.info("开始处理验票文档..........");
		int tflg=0;//记录有多少文件被处理
		List<File> files=StreamUtil.getFile();
		for (File file : files) {
			boolean flg=true;
			List<TicketCheked> lists=StreamUtil.getTickeds(file);
			for (TicketCheked tc : lists) {
				Map<String, Object> paras=new HashMap<String,Object>();
				paras.put("userId",tc.getTicketOwner());
				paras.put("activityId",tc.getAcitivityId());
				paras.put("useNum",tc.getUseNum());
				try {	
					String temp=HttpUtils.POST(Constants.TICKET_CHECH_URL, paras);
					logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>请求结果："+temp);
					int status=JSON.parseObject(temp).getInteger("status");
					if(status!=200){
						logger.info("！！！！！！！！！！！！！！！！！！！！！！！！！处理文档某一数据失败.....该文档是："+file.getName()+"  该数据userId:"+tc.getTicketOwner());
						flg=false;
						break;//停止该文件的数据循环
					}
				} catch (Exception e) {
					logger.info("！！！！！！！！！！！！！！！！！！！！！！！！！处理验票文档失败.....该文档是："+file.getName(),e);
					flg=false;
				}
			}
			if(flg){
				tflg++;
				StreamUtil.fileNameCache.add(file.getName());
			}
		}
		return tflg;
	}
}
