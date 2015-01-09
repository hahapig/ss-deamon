package com.taihe.schedule.job.fund.support;

import java.util.ArrayList;
import java.util.List;

import com.taihe.schedule.entity.Activity;
import com.taihe.schedule.entity.Fund;


public class ActivityFundWrapper {
	//基金
	private Fund fund;
	//和该基金相关的活动信息
	private List<Activity> activities = new ArrayList<Activity>();
	
	public List<Activity> getActivities(){
		return activities;
	}
	public void setActivities(List<Activity> activities){
		this.activities = activities;
	}
	public Fund getFund(){
		return fund;
	}
	public void setFund(Fund fund){
		this.fund = fund;
	}
}
