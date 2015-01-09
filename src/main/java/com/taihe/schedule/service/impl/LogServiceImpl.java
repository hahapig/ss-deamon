package com.taihe.schedule.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taihe.schedule.core.dynamic.DataSource;
import com.taihe.schedule.core.dynamic.DataSourceConst;
import com.taihe.schedule.core.persist.Persistence;
import com.taihe.schedule.entity.ErrorLogInfo;
import com.taihe.schedule.service.LogService;
@DataSource(DataSourceConst.MYSQL_STOCK_DB)
@Service
public class LogServiceImpl implements LogService{
	
	private static final String NANEMSPACE = "logService.";
	
	@Autowired
	private Persistence persistence;

	@Override
	public void insertErrorLog(ErrorLogInfo errorLogInfo){
		persistence.insert(NANEMSPACE+"insertErrorLog", errorLogInfo);
	}
}
