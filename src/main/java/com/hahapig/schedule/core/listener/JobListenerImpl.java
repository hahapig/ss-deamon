package com.hahapig.schedule.core.listener;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Deprecated
public class JobListenerImpl implements JobListener{
	
private static final String NAME = "default_JobListener";
	
	final static Logger logger = LoggerFactory.getLogger(JobListenerImpl.class);

	@Override
	public String getName(){
		return NAME;
	}

	@Override
	public void jobToBeExecuted(JobExecutionContext context){
		logger.debug("job开始执行："+context.getJobDetail().getKey().toString());
		
	}

	@Override
	public void jobExecutionVetoed(JobExecutionContext context){
		
	}

	@Override
	public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException){
		
	}
}
