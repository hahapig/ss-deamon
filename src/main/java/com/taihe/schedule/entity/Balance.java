package com.taihe.schedule.entity;

import java.math.BigDecimal;


public class Balance {
	private int id;
	private String activityid;
	private int userid;
	private BigDecimal consigedamount;
	private int type;//是否符合返现条件 0：不符合 1：符合
	
	public int getId(){
		return id;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public String getActivityid(){
		return activityid;
	}
	
	public void setActivityid(String activityid){
		this.activityid = activityid;
	}
	
	public int getUserid(){
		return userid;
	}
	
	public void setUserid(int userid){
		this.userid = userid;
	}
	
	public BigDecimal getConsigedamount(){
		return consigedamount;
	}
	
	public void setConsigedamount(BigDecimal consigedamount){
		this.consigedamount = consigedamount;
	}
	
	public int getType(){
		return type;
	}
	
	public void setType(int type){
		this.type = type;
	}
}
