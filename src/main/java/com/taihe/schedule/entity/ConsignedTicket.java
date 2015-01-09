package com.taihe.schedule.entity;

import java.math.BigDecimal;
import java.util.Date;


public class ConsignedTicket {
	
	private int consignationid;
	private String activityid;
	private int ticketid;
	private int orderid;
	private Date consignation_time;
	private int fromuser;
	private int touser;
	private int type;//0：不成功 1：成功 2：已过期
	private BigDecimal price;
	
	public int getConsignationid(){
		return consignationid;
	}
	
	public void setConsignationid(int consignationid){
		this.consignationid = consignationid;
	}
	
	public String getActivityid(){
		return activityid;
	}
	
	public void setActivityid(String activityid){
		this.activityid = activityid;
	}
	
	public int getTicketid(){
		return ticketid;
	}
	
	public void setTicketid(int ticketid){
		this.ticketid = ticketid;
	}
	
	public int getOrderid(){
		return orderid;
	}
	
	public void setOrderid(int orderid){
		this.orderid = orderid;
	}
	
	public Date getConsignation_time(){
		return consignation_time;
	}
	
	public void setConsignation_time(Date consignation_time){
		this.consignation_time = consignation_time;
	}
	
	public int getFromuser(){
		return fromuser;
	}
	
	public void setFromuser(int fromuser){
		this.fromuser = fromuser;
	}
	
	public int getTouser(){
		return touser;
	}
	
	public void setTouser(int touser){
		this.touser = touser;
	}
	
	public int getType(){
		return type;
	}
	
	public void setType(int type){
		this.type = type;
	}
	
	public BigDecimal getPrice(){
		return price;
	}
	
	public void setPrice(BigDecimal price){
		this.price = price;
	}
	
}
