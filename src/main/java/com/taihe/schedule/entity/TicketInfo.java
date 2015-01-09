package com.taihe.schedule.entity;

import java.util.Date;


public class TicketInfo {
	private int ticketId;
	private String activityId;
	private String ticketCode;
	private long ticketOwner;
	private Date createTime;
	private Date exchangeTime;
	private int status;
	private int consigned = 1;
	private int checked;
	
	public int getTicketId(){
		return ticketId;
	}
	
	public void setTicketId(int ticketId){
		this.ticketId = ticketId;
	}
	
	public String getActivityId(){
		return activityId;
	}
	
	public void setActivityId(String activityId){
		this.activityId = activityId;
	}
	
	
	public long getTicketOwner() {
		return ticketOwner;
	}

	public void setTicketOwner(long ticketOwner) {
		this.ticketOwner = ticketOwner;
	}

	public Date getCreateTime(){
		return createTime;
	}
	
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}
	
	public Date getExchangeTime(){
		return exchangeTime;
	}
	
	public void setExchangeTime(Date exchangeTime){
		this.exchangeTime = exchangeTime;
	}

	public String getTicketCode(){
		return ticketCode;
	}

	public void setTicketCode(String ticketCode){
		this.ticketCode = ticketCode;
	}

	public int getStatus(){
		return status;
	}

	public void setStatus(int status){
		this.status = status;
	}

	public int getConsigned(){
		return consigned;
	}

	public void setConsigned(int consigned){
		this.consigned = consigned;
	}

	public int getChecked(){
		return checked;
	}

	public void setChecked(int checked){
		this.checked = checked;
	}

}
