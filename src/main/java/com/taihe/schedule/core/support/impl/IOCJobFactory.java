package com.taihe.schedule.core.support.impl;

import java.lang.reflect.Method;

import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.spi.JobFactory;
import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.ReflectionUtils;

import com.taihe.schedule.core.support.Constant;
import com.taihe.schedule.core.support.JobDelegate;

public class IOCJobFactory implements JobFactory,ApplicationContextAware{
	
	private ApplicationContext applicationContext;

	@Override
	public Job newJob(TriggerFiredBundle bundle, Scheduler scheduler) throws SchedulerException{
		JobDetail detail = bundle.getJobDetail();
		String methodName = (String) detail.getJobDataMap().get(Constant.CONMMON_NAMED_METHOD);
		String serviceName = (String) detail.getJobDataMap().get(Constant.CONMMON_NAMED_SERVICE);
		Object service = applicationContext.getBean(serviceName);
		if(service==null){
			throw new SchedulerException("无法找到服务："+serviceName);
		}
		Method method = ReflectionUtils.findMethod(service.getClass(), methodName);
		if(method==null){
			throw new SchedulerException("无法找到服务："+serviceName+"."+methodName);
		}
		return new JobDelegate(service, method);
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException{
		this.applicationContext = applicationContext;
	}
}
