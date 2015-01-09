package com.taihe.schedule.entity;


/**
 * 验票的模型
 * */
public class TicketCheked {
	private int useNum; 
	private int totalNum;
	private short protype;
	private String ticketCode;
	private String acitivityId;
	private long ticketOwner;
	
	public int getUseNum() {
		return useNum;
	}
	public void setUseNum(int useNum) {
		this.useNum = useNum;
	}
	public int getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}
	public short getProtype() {
		return protype;
	}
	public void setProtype(short protype) {
		this.protype = protype;
	}
	public String getTicketCode() {
		return ticketCode;
	}
	public void setTicketCode(String ticketCode) {
		this.ticketCode = ticketCode;
	}
	public String getAcitivityId() {
		return acitivityId;
	}
	public void setAcitivityId(String acitivityId) {
		this.acitivityId = acitivityId;
	}
	public long getTicketOwner() {
		return ticketOwner;
	}
	public void setTicketOwner(long ticketOwner) {
		this.ticketOwner = ticketOwner;
	}
}
