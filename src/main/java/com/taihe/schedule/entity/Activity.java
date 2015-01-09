package com.taihe.schedule.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.taihe.schedule.core.persist.BaseUser;
import com.taihe.schedule.util.Constants;

public class Activity {

	private String id;

	private boolean payed = false;// 发起人是否已付款 默认未支付

	private String avatar;// 活动封面图（小） 100x300

	private String title;// 活动标题

	private BaseUser performer;// 艺人

	private BaseUser site;// 场地

	private String document;// 活动详细文案(含html标签)

	private String description;// 活动简介

	private double hasRaise = 0d;// 已筹资 默认为0

	private double planRaise;// 计划筹资

	private Date preparation;// 最短筹备期

	private boolean bestSelected = false;// 是否是精选 默认否

	private String poster;// 背景海报地址 1000x300

	private ActivityPrice prices;//活动相关价格
	
	private boolean selfOrganizer = false;// 是否自己主办

	private boolean fullStart;// 是否全额发起

	private boolean deposited;// 是否支付定金

	private BaseUser startUser;// 发起人

	private Date showStartDate;// 演出开始时间

	private Date showEndDate;// 演出结束时间

	private int level = Constants.WAIT_PAY;// 活动档期 默认待付款

	private int city;// 城市编码

	private String address;// 演出地址

	private List<Integer> style = new ArrayList<>();// 活动风格

	private List<String> album = new ArrayList<>();// 海报相册

	private int praise = 0;// 赞的数量 默认为0

	private List<String> playlist = new ArrayList<>();// 艺人歌单 默认为空

	private int type;// 巡演、普通发起、发布

	private String tourId;// 巡演id

	private int totalTickets;// 票总量

	private FundInfo fund;// 基金信息

	private Date updateTime;// 最后更新的时间
	
    private int dealType;//停止预售后的判断  1：停止售票  2：原价售票

    private int costPrice;//活动原价
    
    
	public int getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(int costPrice) {
		this.costPrice = costPrice;
	}

	public int getDealType() {
		return dealType;
	}

	public void setDealType(int dealType) {
		this.dealType = dealType;
	}

	public String getId(){
		return id;
	}

	public void setId(String id){
		this.id = id;
	}

	public boolean isPayed(){
		return payed;
	}

	public void setPayed(boolean payed){
		this.payed = payed;
	}

	public String getAvatar(){
		return avatar;
	}

	public void setAvatar(String avatar){
		this.avatar = avatar;
	}

	public String getTitle(){
		return title;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public BaseUser getPerformer(){
		return performer;
	}

	public void setPerformer(BaseUser performer){
		this.performer = performer;
	}

	public BaseUser getSite(){
		return site;
	}

	public void setSite(BaseUser site){
		this.site = site;
	}

	public String getDocument(){
		return document;
	}

	public void setDocument(String document){
		this.document = document;
	}

	public String getDescription(){
		return description;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public double getHasRaise(){
		return hasRaise;
	}

	public void setHasRaise(double hasRaise){
		this.hasRaise = hasRaise;
	}

	public double getPlanRaise(){
		return planRaise;
	}

	public void setPlanRaise(double planRaise){
		this.planRaise = planRaise;
	}

	public Date getPreparation(){
		return preparation;
	}

	public void setPreparation(Date preparation){
		this.preparation = preparation;
	}

	public boolean isBestSelected(){
		return bestSelected;
	}

	public void setBestSelected(boolean bestSelected){
		this.bestSelected = bestSelected;
	}

	public String getPoster(){
		return poster;
	}

	public void setPoster(String poster){
		this.poster = poster;
	}

	public boolean isSelfOrganizer(){
		return selfOrganizer;
	}

	public void setSelfOrganizer(boolean selfOrganizer){
		this.selfOrganizer = selfOrganizer;
	}

	public boolean isFullStart(){
		return fullStart;
	}

	public void setFullStart(boolean fullStart){
		this.fullStart = fullStart;
	}

	public boolean isDeposited(){
		return deposited;
	}

	public void setDeposited(boolean deposited){
		this.deposited = deposited;
	}

	public BaseUser getStartUser(){
		return startUser;
	}

	public void setStartUser(BaseUser startUser){
		this.startUser = startUser;
	}

	public Date getShowStartDate(){
		return showStartDate;
	}

	public void setShowStartDate(Date showStartDate){
		this.showStartDate = showStartDate;
	}

	public Date getShowEndDate(){
		return showEndDate;
	}

	public void setShowEndDate(Date showEndDate){
		this.showEndDate = showEndDate;
	}

	public int getLevel(){
		return level;
	}

	public void setLevel(int level){
		this.level = level;
	}

	public int getCity(){
		return city;
	}

	public void setCity(int city){
		this.city = city;
	}

	public String getAddress(){
		return address;
	}

	public void setAddress(String address){
		this.address = address;
	}

	public List<Integer> getStyle(){
		return style;
	}

	public void setStyle(List<Integer> style){
		this.style = style;
	}

	public List<String> getAlbum(){
		return album;
	}

	public void setAlbum(List<String> album){
		this.album = album;
	}

	public int getPraise(){
		return praise;
	}

	public void setPraise(int praise){
		this.praise = praise;
	}

	public List<String> getPlaylist(){
		return playlist;
	}

	public void setPlaylist(List<String> playlist){
		this.playlist = playlist;
	}

	public int getType(){
		return type;
	}

	public void setType(int type){
		this.type = type;
	}

	public String getTourId(){
		return tourId;
	}

	public void setTourId(String tourId){
		this.tourId = tourId;
	}

	public int getTotalTickets(){
		return totalTickets;
	}

	public void setTotalTickets(int totalTickets){
		this.totalTickets = totalTickets;
	}

	public FundInfo getFund(){
		return fund;
	}

	public void setFund(FundInfo fund){
		this.fund = fund;
	}

	public Date getUpdateTime(){
		return updateTime;
	}

	public void setUpdateTime(Date updateTime){
		this.updateTime = updateTime;
	}

	public ActivityPrice getPrices(){
		return prices;
	}

	public void setPrices(ActivityPrice prices){
		this.prices = prices;
	}

	@Override
	public String toString() {
		return "Activity [id=" + id + ", payed=" + payed + ", avatar=" + avatar
				+ ", title=" + title + ", performer=" + performer + ", site="
				+ site + ", document=" + document + ", description="
				+ description + ", hasRaise=" + hasRaise + ", planRaise="
				+ planRaise + ", preparation=" + preparation
				+ ", bestSelected=" + bestSelected + ", poster=" + poster
				+ ", prices=" + prices + ", selfOrganizer=" + selfOrganizer
				+ ", fullStart=" + fullStart + ", deposited=" + deposited
				+ ", startUser=" + startUser + ", showStartDate="
				+ showStartDate + ", showEndDate=" + showEndDate + ", level="
				+ level + ", city=" + city + ", address=" + address
				+ ", style=" + style + ", album=" + album + ", praise="
				+ praise + ", playlist=" + playlist + ", type=" + type
				+ ", tourId=" + tourId + ", totalTickets=" + totalTickets
				+ ", fund=" + fund + ", updateTime=" + updateTime
				+ ", dealType=" + dealType + "]";
	}
}
