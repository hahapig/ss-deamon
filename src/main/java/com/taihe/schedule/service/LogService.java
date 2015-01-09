package com.taihe.schedule.service;

import com.taihe.schedule.entity.ErrorLogInfo;


public interface LogService {
	
	public void insertErrorLog(ErrorLogInfo errorLogInfo);
}
