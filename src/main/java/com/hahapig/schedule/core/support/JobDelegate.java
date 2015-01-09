package com.hahapig.schedule.core.support;

import java.lang.reflect.Method;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ReflectionUtils;


public class JobDelegate implements Job{
	
	final static Logger logger = LoggerFactory.getLogger(JobDelegate.class);
	
	private Object service;
	
	private Method method;
	
	public JobDelegate(Object service,Method method){
		this.method = method;
		this.service = service;
	}

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException{
		try {
			ReflectionUtils.invokeMethod(method, service);
		} catch (Throwable e) {
			logger.error("定时任务执行失败",e);
		}
		
	}
}
