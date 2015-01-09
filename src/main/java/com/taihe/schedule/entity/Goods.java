package com.taihe.schedule.entity;

import java.util.Date;
/**
 * 商品模型
 * */
public class Goods {

	private Long productId;        //商品ID  
	
	private String productName;     //商品名称
	
	private String siteId;          //场地ID
	
	private Double oriPrice;       //原价
	
	private Double retPrice;       //零售价
	
	private Double pacPrice;       //套餐价
	
	private String picAddr;        //图片地址
	
	private String norms;          //规格
	
	private Integer period;         //有效期
	
	private Short status;          //状态
	
	private Short proType;          //类型
	
	private String proDes;          //商品描述
	
	private Date endTime;          //下架时间
	
	private Date createTime;       //创建时间
	
	private Date opeTime;          //更新时间

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getSiteId() {
		return siteId;
	}

	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}

	public Double getOriPrice() {
		return oriPrice;
	}

	public void setOriPrice(Double oriPrice) {
		this.oriPrice = oriPrice;
	}

	public Double getRetPrice() {
		return retPrice;
	}

	public void setRetPrice(Double retPrice) {
		this.retPrice = retPrice;
	}

	public Double getPacPrice() {
		return pacPrice;
	}

	public void setPacPrice(Double pacPrice) {
		this.pacPrice = pacPrice;
	}

	public String getPicAddr() {
		return picAddr;
	}

	public void setPicAddr(String picAddr) {
		this.picAddr = picAddr;
	}

	public String getNorms() {
		return norms;
	}

	public void setNorms(String norms) {
		this.norms = norms;
	}

	public Integer getPeriod() {
		return period;
	}

	public void setPeriod(Integer period) {
		this.period = period;
	}

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public Short getProType() {
		return proType;
	}

	public void setProType(Short proType) {
		this.proType = proType;
	}

	public String getProDes() {
		return proDes;
	}

	public void setProDes(String proDes) {
		this.proDes = proDes;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getOpeTime() {
		return opeTime;
	}

	public void setOpeTime(Date opeTime) {
		this.opeTime = opeTime;
	}

	@Override
	public String toString() {
		return "Goods [productId=" + productId + ", productName=" + productName
				+ ", siteId=" + siteId + ", oriPrice=" + oriPrice
				+ ", retPrice=" + retPrice + ", pacPrice=" + pacPrice
				+ ", picAddr=" + picAddr + ", norms=" + norms + ", period="
				+ period + ", status=" + status + ", proType=" + proType
				+ ", proDes=" + proDes + ", endTime=" + endTime
				+ ", createTime=" + createTime + ", opeTime=" + opeTime + "]";
	}

	
}
