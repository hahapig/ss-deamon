package com.taihe.schedule.entity;

import java.util.Date;


public class ConsignationTicket {
	private int consignationId;
	private int ticketId;
	private Date consignationTime;
	private long fromuser;
	private long toUser;
	private int type;
	private String activityId;
	private long orderId;
	private int price;
	
	public int getConsignationId(){
		return consignationId;
	}
	
	public void setConsignationId(int consignationId){
		this.consignationId = consignationId;
	}
	
	public int getTicketId(){
		return ticketId;
	}
	
	public void setTicketId(int ticketId){
		this.ticketId = ticketId;
	}
	
	public Date getConsignationTime(){
		return consignationTime;
	}
	
	public void setConsignationTime(Date consignationTime){
		this.consignationTime = consignationTime;
	}
	
	
	public long getFromuser() {
		return fromuser;
	}

	public void setFromuser(long fromuser) {
		this.fromuser = fromuser;
	}

	public long getToUser() {
		return toUser;
	}

	public void setToUser(long toUser) {
		this.toUser = toUser;
	}

	public int getType(){
		return type;
	}
	
	public void setType(int type){
		this.type = type;
	}

	public String getActivityId() {
		return activityId;
	}

	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}

	
	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	

}
