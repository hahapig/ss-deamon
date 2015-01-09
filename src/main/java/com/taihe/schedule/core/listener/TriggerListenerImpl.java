package com.taihe.schedule.core.listener;

import org.quartz.JobExecutionContext;
import org.quartz.Trigger;
import org.quartz.Trigger.CompletedExecutionInstruction;
import org.quartz.TriggerListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Deprecated
public class TriggerListenerImpl implements TriggerListener{
	private static final String NAME = "default_TriggerListener";
	
	final static Logger logger = LoggerFactory.getLogger(TriggerListenerImpl.class);

	@Override
	public String getName(){
		return NAME;
	}

	@Override
	public void triggerFired(Trigger trigger, JobExecutionContext context){
		logger.debug(trigger.getKey().toString()+"被触发");
	}

	@Override
	public boolean vetoJobExecution(Trigger trigger, JobExecutionContext context){
		logger.debug(context.getFireInstanceId()+"被触发");
		return false;
	}

	@Override
	public void triggerMisfired(Trigger trigger){
		logger.error(trigger.getKey().toString()+"触发失败，没找到对应的JOB");
		
	}

	@Override
	public void triggerComplete(Trigger trigger, JobExecutionContext context,
			CompletedExecutionInstruction triggerInstructionCode){
		
	}
}
