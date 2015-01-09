package com.hahapig.schedule.core.support.impl;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

import java.lang.reflect.Method;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.utils.ClassUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.util.StringUtils;

import com.hahapig.schedule.core.support.Constant;
import com.hahapig.schedule.core.support.ExtendBuilder;
import com.hahapig.schedule.core.support.annotation.ExtendDisallowConcurrent;
import com.hahapig.schedule.core.support.annotation.ExtendPersistJobDataAfterExecution;
import com.hahapig.schedule.core.support.annotation.ScheduleAnnotation;
import com.hahapig.schedule.core.utils.ConfigReader;

public class ScheduleAnnotationProcessor implements BeanPostProcessor {
	
	@Autowired
	private Scheduler scheduler;
	
	@Autowired
	private ConfigReader configReader;

	final static Logger logger = LoggerFactory.getLogger(ScheduleAnnotationProcessor.class);

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException{
		// do nothing
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException{
		ScheduleAnnotation annotation = ClassUtils.getAnnotation(bean.getClass(), ScheduleAnnotation.class);
		if (annotation != null) {
			doWithAnnotation(bean, beanName, annotation);
		}
		return bean;
	}

	private void doWithAnnotation(Object bean, String beanName, ScheduleAnnotation annotation){
		String groupName = StringUtils.hasLength(annotation.jobGroup()) ? annotation.jobGroup() : beanName;
		Method[] methods = bean.getClass().getDeclaredMethods();
		
		for (int i = 0; i < methods.length; i++) {
			Method method =  methods[i];
			if(!method.isAnnotationPresent(ScheduleAnnotation.class)){
				continue;
			}
			
			ScheduleAnnotation methodAnnotation =method.getAnnotation(ScheduleAnnotation.class);// method.getDeclaredAnnotation(ScheduleAnnotation.class);
			String jobName = StringUtils.hasLength(methodAnnotation.jobName())?methodAnnotation.jobName():method.getName();
			boolean isConcurrent = method.isAnnotationPresent(ExtendDisallowConcurrent.class);
			boolean isPersist = method.isAnnotationPresent(ExtendPersistJobDataAfterExecution.class);
			
			logger.debug("开始生成JOB："+beanName +"."+ method.getName()+"--->"+groupName+"."+jobName);
			JobDetail job = ExtendBuilder.newExtendBuilder()
					.JobClass(bean.getClass())
					.description(methodAnnotation.description())
					.usingJobData(Constant.CONMMON_NAMED_METHOD, method.getName())
					.usingJobData(Constant.CONMMON_NAMED_SERVICE, beanName)
					.setConcurrentExectionDisallowed(isConcurrent)
					.setPersistJobDataAfterExecution(isPersist)
					.withIdentity(jobName, groupName)
					.build();
			
			//cornexpression优先级为---类注解-->方法注解---->配置
			String cronExpression = StringUtils.hasLength(methodAnnotation.cronExpression())?methodAnnotation.cronExpression():annotation.cronExpression();
			cronExpression = configReader.getCronExpression(groupName+"."+jobName);
			if(!StringUtils.hasLength(cronExpression)){
				throw new IllegalArgumentException(beanName+"."+method.getName()+"没有CronExpression请检查");
			}
			
			boolean raplaceExisted = methodAnnotation.replaceExsitedJob();
			
			CronTrigger trigger = newTrigger()
				    .withIdentity(Constant.CONMMON_TRIGGER_PREFIX+jobName, Constant.CONMMON_TRIGGER_PREFIX+groupName)
				    .withSchedule(cronSchedule(cronExpression))
				    .build();
			
			try {
				if(scheduler.checkExists(job.getKey())){
					logger.debug("已经存在JOB："+job.getKey());
					if(raplaceExisted){
						logger.debug("替换已经存在JOB："+job.getKey());
						scheduler.deleteJob(job.getKey());
						scheduler.scheduleJob(job, trigger);
					}
				}else{
					scheduler.scheduleJob(job, trigger);
				}
				logger.debug("加入调度队列，JOB："+job.getKey());
			} catch (SchedulerException e) {
				logger.error("初始化调度任务失败",e);
				throw new RuntimeException("初始化调度任务失败",e);
			}
		}
	}
}
