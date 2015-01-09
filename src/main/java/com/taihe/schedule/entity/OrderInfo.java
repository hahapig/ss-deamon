package com.taihe.schedule.entity;

import java.math.BigDecimal;
import java.util.Date;


public class OrderInfo {
	private int orderId;
	private Date vdate;
	private int orderstatus;
	private int ordertype;
	private int paymentstatus;
	private BigDecimal totalAmount;
	private int totalNum;
	private BigDecimal amountpayable;
	private BigDecimal amountpaid;
	private int customer_id;
	
	//---------部分订单详细信息
	private int orderdetail_id;
	private String activity_id;
	private BigDecimal price;
	private int num;
	private Date ddate;
	private int mid_stockid;
	
	public int getOrderId(){
		return orderId;
	}
	
	public void setOrderId(int orderId){
		this.orderId = orderId;
	}
	
	public int getOrderstatus(){
		return orderstatus;
	}
	
	public void setOrderstatus(int orderstatus){
		this.orderstatus = orderstatus;
	}
	
	public int getOrdertype(){
		return ordertype;
	}
	
	public void setOrdertype(int ordertype){
		this.ordertype = ordertype;
	}
	
	public int getPaymentstatus(){
		return paymentstatus;
	}
	
	public void setPaymentstatus(int paymentstatus){
		this.paymentstatus = paymentstatus;
	}
	
	public BigDecimal getTotalAmount(){
		return totalAmount;
	}
	
	public void setTotalAmount(BigDecimal totalAmount){
		this.totalAmount = totalAmount;
	}
	
	public int getTotalNum(){
		return totalNum;
	}
	
	public void setTotalNum(int totalNum){
		this.totalNum = totalNum;
	}
	
	public BigDecimal getAmountpayable(){
		return amountpayable;
	}
	
	public void setAmountpayable(BigDecimal amountpayable){
		this.amountpayable = amountpayable;
	}
	
	public BigDecimal getAmountpaid(){
		return amountpaid;
	}
	
	public void setAmountpaid(BigDecimal amountpaid){
		this.amountpaid = amountpaid;
	}
	
	public int getOrderdetail_id(){
		return orderdetail_id;
	}
	
	public void setOrderdetail_id(int orderdetail_id){
		this.orderdetail_id = orderdetail_id;
	}
	
	public String getActivity_id(){
		return activity_id;
	}
	
	public void setActivity_id(String activity_id){
		this.activity_id = activity_id;
	}
	
	public BigDecimal getPrice(){
		return price;
	}
	
	public void setPrice(BigDecimal price){
		this.price = price;
	}
	
	public int getNum(){
		return num;
	}
	
	public void setNum(int num){
		this.num = num;
	}
	
	public Date getDdate(){
		return ddate;
	}
	
	public void setDdate(Date ddate){
		this.ddate = ddate;
	}
	
	public int getMid_stockid(){
		return mid_stockid;
	}
	
	public void setMid_stockid(int mid_stockid){
		this.mid_stockid = mid_stockid;
	}

	public Date getVdate(){
		return vdate;
	}

	public void setVdate(Date vdate){
		this.vdate = vdate;
	}

	public int getCustomer_id(){
		return customer_id;
	}

	public void setCustomer_id(int customer_id){
		this.customer_id = customer_id;
	}
	
}
