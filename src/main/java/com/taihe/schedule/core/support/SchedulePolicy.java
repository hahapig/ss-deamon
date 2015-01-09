package com.taihe.schedule.core.support;


/**
 * @Description 调度策略，使用这个策略来生成contriger以及job代理
 * @author yangt 
 * @date 2014年8月20日 上午11:39:25 
 */
@Deprecated
public class SchedulePolicy {
	private String cronExpression;
	private String jobGroup;
	private String jobName;
	private boolean disallowConcurrentExecution;
	private boolean persistJobDataAfterExecution;
	
	public String getCronExpression(){
		return cronExpression;
	}
	
	public void setCronExpression(String cronExpression){
		this.cronExpression = cronExpression;
	}
	
	public String getJobGroup(){
		return jobGroup;
	}
	
	public void setJobGroup(String jobGroup){
		this.jobGroup = jobGroup;
	}
	
	public String getJobName(){
		return jobName;
	}
	
	public void setJobName(String jobName){
		this.jobName = jobName;
	}

	public boolean isDisallowConcurrentExecution(){
		return disallowConcurrentExecution;
	}

	public void setDisallowConcurrentExecution(boolean disallowConcurrentExecution){
		this.disallowConcurrentExecution = disallowConcurrentExecution;
	}

	public boolean isPersistJobDataAfterExecution(){
		return persistJobDataAfterExecution;
	}

	public void setPersistJobDataAfterExecution(boolean persistJobDataAfterExecution){
		this.persistJobDataAfterExecution = persistJobDataAfterExecution;
	}
	
}
