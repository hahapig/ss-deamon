package com.taihe.schedule.job.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taihe.schedule.core.support.annotation.ExtendDisallowConcurrent;
import com.taihe.schedule.core.support.annotation.ScheduleAnnotation;
import com.taihe.schedule.entity.Goods;
import com.taihe.schedule.job.StockJob;
import com.taihe.schedule.service.StockService;
import com.taihe.schedule.util.StreamUtil;

@ScheduleAnnotation(jobGroup = "stockJob")
@Service
public class StockJobImpl implements StockJob {
	final static Logger logger = LoggerFactory.getLogger(StockJobImpl.class);
	@Autowired
	private StockService stockService;
	
	/**
	 * 清理过期商品
	 * */
	@ExtendDisallowConcurrent
	@ScheduleAnnotation()
	public void cleanExpiredGoods() {
		int flg=0;
		logger.info("库存中心，过期商品定时任务启动... "+new Date());
		try {
			List<Goods> goods=stockService.getExpiredGoods();
			flg=stockService.updateExpiredGoods(goods);
		} catch (Exception e) {
			logger.error("库存中心，过期商品定时任务失败...",e);
		}
		logger.debug("库存中心，过期商品定时任务结束... "+new Date()+" 共："+flg+"个商品被更新");
	}
	
	/**
	 * 定时更新票
	 * */
	@ExtendDisallowConcurrent
	@ScheduleAnnotation()
	public void checkedTickets() {
		logger.info("库存中心，更新验票结果定时任务启动... "+new Date());
		try {
			//业务逻辑
			int flg=stockService.checkedTickets();
			if(flg>0){
				StreamUtil.saveCache();//如果有处理成功的文件，就更新一下本地记录
			}
		} catch (Exception e) {
			logger.error("库存中心，更新验票结果定时任务失败... ",e);
		}
		logger.debug("库存中心，更新验票结果定时任务结束... "+new Date());
	}

}
