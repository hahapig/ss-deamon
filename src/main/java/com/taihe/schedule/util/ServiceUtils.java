package com.taihe.schedule.util;

import java.util.Date;

import com.taihe.schedule.entity.ErrorLogInfo;


public abstract class ServiceUtils {
	
	public static ErrorLogInfo getLogInfo(String buziId,int buziType,String errorInfo ){
		ErrorLogInfo errorLogInfo = new ErrorLogInfo();
		errorLogInfo.setBuziId(buziId);
		errorLogInfo.setBuziType(buziType);
		errorLogInfo.setDataTime(new Date());
		errorLogInfo.setFailedInfo(errorInfo);
		return errorLogInfo;
	}
}
