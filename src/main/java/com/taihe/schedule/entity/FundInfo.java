package com.taihe.schedule.entity;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Field;


public class FundInfo {
	@Field("id")
	private String fundId;
	private String id;//基金Id
	private String name;//基金名称
    private int status;//申请状态 1申请中 2申请成功 3申请失败
    private Date time;//申请时间
    
	public String getId(){
		return id;
	}
	
	public void setId(String id){
		this.id = id;
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public int getStatus(){
		return status;
	}
	
	public void setStatus(int status){
		this.status = status;
	}
	
	public Date getTime(){
		return time;
	}
	
	public void setTime(Date time){
		this.time = time;
	}

	public String getFundId(){
		return fundId;
	}

	public void setFundId(String fundId){
		this.fundId = fundId;
	}
	
}
