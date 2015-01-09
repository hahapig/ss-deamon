package com.hahapig.schedule.core.support;

import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.utils.Key;

public class ExtendBuilder  {

	private JobKey key;

	private Class<?> jobClass;

	private String description;

	private boolean durability;

	private boolean shouldRecover;

	private JobDataMap jobDataMap = new JobDataMap();;
	
	private boolean isPersistJobDataAfterExecution;
	
	private boolean isConcurrentExectionDisallowed;

	public static ExtendBuilder newExtendBuilder(){
		return new ExtendBuilder();
	}

	public JobDetail build(){
		ExtendJobDetail job = new ExtendJobDetail();
		job.setConcurrentExectionDisallowed(true);
		job.setPersistJobDataAfterExecution(isPersistJobDataAfterExecution);
		job.setExtendJobClass(jobClass);
		job.setDescription(description);
		if (key == null)
			key = new JobKey(Key.createUniqueName(null), null);
		job.setKey(key);
		job.setDurability(durability);
		job.setRequestsRecovery(shouldRecover);
		if (!jobDataMap.isEmpty())
			job.setJobDataMap(jobDataMap);
		return job;
	}
	
	public ExtendBuilder description(String description) {
		this.description = description;
        return this;
    }
	
	public ExtendBuilder withIdentity(String name, String group) {
        key = new JobKey(name, group);
        return this;
    }
	
	public ExtendBuilder usingJobData(String dataKey, String value) {
        this.jobDataMap.put(dataKey, value);
        return this;
    }

	public ExtendBuilder JobClass(Class<?> jobClass){
		this.jobClass = jobClass;
		return this;
	}

	public boolean isPersistJobDataAfterExecution(){
		return isPersistJobDataAfterExecution;
	}

	public ExtendBuilder setPersistJobDataAfterExecution(boolean isPersistJobDataAfterExecution){
		this.isPersistJobDataAfterExecution = isPersistJobDataAfterExecution;
		return this;
	}

	public boolean isConcurrentExectionDisallowed(){
		return isConcurrentExectionDisallowed;
	}

	public ExtendBuilder setConcurrentExectionDisallowed(boolean isConcurrentExectionDisallowed){
		this.isConcurrentExectionDisallowed = isConcurrentExectionDisallowed;
		return this;
	}
}
