package com.taihe.schedule.entity;

import java.util.Date;


public class ErrorLogInfo {
	
	private int id;//主键
	private String failedInfo;//失败信息
	private Date dataTime;//数据时间
	private int buziType;//1，为订单过期处理，2为活动到期处理
	private String buziId;//业务处理的标示符 orderId .
	
	public int getId(){
		return id;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public String getFailedInfo(){
		return failedInfo;
	}
	
	public void setFailedInfo(String failedInfo){
		this.failedInfo = failedInfo;
	}
	
	public Date getDataTime(){
		return dataTime;
	}
	
	public void setDataTime(Date dataTime){
		this.dataTime = dataTime;
	}
	
	public int getBuziType(){
		return buziType;
	}
	
	public void setBuziType(int buziType){
		this.buziType = buziType;
	}
	
	public String getBuziId(){
		return buziId;
	}
	
	public void setBuziId(String buziId){
		this.buziId = buziId;
	}
	
}
