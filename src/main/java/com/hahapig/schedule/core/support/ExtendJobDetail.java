package com.hahapig.schedule.core.support;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.impl.JobDetailImpl;

public class ExtendJobDetail extends JobDetailImpl implements Cloneable, java.io.Serializable {

	private static final long serialVersionUID = -6069784757781506897L;

	private Class<?> ObjectClass;
	
	private boolean isPersistJobDataAfterExecution;
	
	private boolean isConcurrentExectionDisallowed;
	
	public ExtendJobDetail(){
		setJobClass(Job.class);
	}
	
	public void setPersistJobDataAfterExecution(boolean isPersistJobDataAfterExecution){
		this.isPersistJobDataAfterExecution = isPersistJobDataAfterExecution;
	}
	
	public void setConcurrentExectionDisallowed(boolean isConcurrentExectionDisallowed){
		this.isConcurrentExectionDisallowed = isConcurrentExectionDisallowed;
	}

	public void setExtendJobClass(Class<?> ObjectClass){
		this.ObjectClass = ObjectClass;
	}

	public boolean isPersistJobDataAfterExecution(){
		return isPersistJobDataAfterExecution;
	}

	public boolean isConcurrentExectionDisallowed(){
		return isConcurrentExectionDisallowed;
	}

	public Class<?> getExtendJobClass(){
		return ObjectClass;
	}
	
	@Override
    public Object clone() {//克隆方法没有测试过，可能有潜在的风险
		ExtendJobDetail copy;
        copy = (ExtendJobDetail) super.clone();
		if (getJobDataMap() != null) {
		    copy.setJobDataMap((JobDataMap) getJobDataMap().clone());
		}

        return copy;
    }
}
