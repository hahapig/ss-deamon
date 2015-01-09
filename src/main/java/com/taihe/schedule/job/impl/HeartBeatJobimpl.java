package com.taihe.schedule.job.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.taihe.schedule.core.support.annotation.ExtendDisallowConcurrent;
import com.taihe.schedule.core.support.annotation.ScheduleAnnotation;
import com.taihe.schedule.job.HeartBeatJob;

//@ScheduleAnnotation(jobGroup="default_heartbeat")
@Service
public class HeartBeatJobimpl implements HeartBeatJob{
	final static Logger logger = LoggerFactory.getLogger(HeartBeatJobimpl.class);
	
	@ExtendDisallowConcurrent
	@ScheduleAnnotation(cronExpression="0/30 * * * * ?")
	public void beat(){
		logger.info("default_heartbeat executed,Thread-name:"+Thread.currentThread().getName());
	}
	
}
