package com.taihe.schedule.entity;

import java.math.BigDecimal;
import java.util.Date;


public class ActReport {
	//活动信息
	private int id;
	private String actname;
	private String activityid;
	private Date acttime;
	private BigDecimal needmoney;//期望筹款
	private BigDecimal finalmoney;//最终凑款
	private int usefund;
	//平台基金
	private BigDecimal fundmoney;
	//艺人信息--佣金
	private BigDecimal artrebate;
	//场地信息--佣金
	private BigDecimal siterebate;
	//库存相关
	private BigDecimal consignincome;//托售收入
	private int consignsum;
	private int ticketamount;
	private BigDecimal ticketsum;
	//计算结果
	private BigDecimal platincome;//平台收入
	private BigDecimal finalgain;//最终收入
	private Date reptime = new Date();//报表时间
	
	public int getId(){
		return id;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public String getActname(){
		return actname;
	}
	
	public void setActname(String actname){
		this.actname = actname;
	}
	
	public String getActivityid(){
		return activityid;
	}
	
	public void setActivityid(String activityid){
		this.activityid = activityid;
	}
	
	public Date getActtime(){
		return acttime;
	}
	
	public void setActtime(Date acttime){
		this.acttime = acttime;
	}
	
	public BigDecimal getNeedmoney(){
		return needmoney;
	}
	
	public void setNeedmoney(BigDecimal needmoney){
		this.needmoney = needmoney;
	}
	
	public BigDecimal getFinalmoney(){
		return finalmoney;
	}
	
	public void setFinalmoney(BigDecimal finalmoney){
		this.finalmoney = finalmoney;
	}
	
	public int getUsefund(){
		return usefund;
	}
	
	public void setUsefund(int usefund){
		this.usefund = usefund;
	}
	
	public BigDecimal getArtrebate(){
		return artrebate;
	}
	
	public void setArtrebate(BigDecimal artrebate){
		this.artrebate = artrebate;
	}
	
	public BigDecimal getSiterebate(){
		return siterebate;
	}
	
	public void setSiterebate(BigDecimal siterebate){
		this.siterebate = siterebate;
	}
	
	public BigDecimal getConsignincome(){
		return consignincome;
	}
	
	public void setConsignincome(BigDecimal consignincome){
		this.consignincome = consignincome;
	}
	
	public int getConsignsum(){
		return consignsum;
	}
	
	public void setConsignsum(int consignsum){
		this.consignsum = consignsum;
	}
	
	public int getTicketamount(){
		return ticketamount;
	}
	
	public void setTicketamount(int ticketamount){
		this.ticketamount = ticketamount;
	}
	
	public BigDecimal getTicketsum(){
		return ticketsum;
	}
	
	public void setTicketsum(BigDecimal ticketsum){
		this.ticketsum = ticketsum;
	}
	
	public BigDecimal getPlatincome(){
		return platincome;
	}
	
	public void setPlatincome(BigDecimal platincome){
		this.platincome = platincome;
	}
	
	public BigDecimal getFinalgain(){
		return finalgain;
	}
	
	public void setFinalgain(BigDecimal finalgain){
		this.finalgain = finalgain;
	}
	
	public Date getReptime(){
		return reptime;
	}
	
	public void setReptime(Date reptime){
		this.reptime = reptime;
	}

	public BigDecimal getFundmoney(){
		return fundmoney;
	}

	public void setFundmoney(BigDecimal fundmoney){
		this.fundmoney = fundmoney;
	}
}
