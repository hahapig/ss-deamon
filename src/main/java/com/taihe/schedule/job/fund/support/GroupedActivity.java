package com.taihe.schedule.job.fund.support;

import java.text.SimpleDateFormat;

import com.taihe.schedule.entity.Activity;

public final class GroupedActivity {

	private Activity activity;

	private String groupKey;

	private int status = 0;// 0代表没有任何状态
	
	private double rate;//进度
	
	public void setStatus(int status){
		activity.setLevel(status);
		this.status = status;
	}

	public GroupedActivity(Activity activity) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yy-MM-dd");
		this.activity = activity;
		groupKey = activity.getSite().getUserId() + dateFormat.format(activity.getShowStartDate());
	}

	public Activity getActivity(){
		return activity;
	}

	public String getGroupKey(){
		return groupKey;
	}

	public int getStatus(){
		return status;
	}

	public boolean equals(Object anObject){
		if (anObject instanceof GroupedActivity) {
			if (((GroupedActivity) anObject).getActivity().getId().equals(activity.getId())) {
				return true;
			}
		}
		return false;
	}
	
	public int hashCode() {
		return activity.getId().hashCode();
	}

	public double getRate(){
		return rate;
	}

	public void setRate(double rate){
		this.rate = rate;
	}
}
