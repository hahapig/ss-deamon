package com.taihe.schedule.entity;

public class Fund {

	private String id;

	private String name;// 基金名称

	private String description;// 基金描述

	private String poster;// 背景海报图片地址

	private String slogan;// 标语

	private String cooperationUnit;// 合作单位

	private String cooperationUrl;// 合作单位URL

	private int releaseForm;// 基金发放形式 1 百分比定期结算制 2 剩余份额终结制

	private int preBalanceDay;// 提前结算天数

	private double amount;// 赞助金额

	private int weekType;// 选择周的类型（演出时间：按每周 or 每月最后一周）

	private int week;// 具体周几

	private double deposit;// 场地定金额

	// private FundLocation location;//演出地点
	// private List<String> video = new ArrayList<>();//基金宣传视频 FundVideoOrPhoto
	// private List<String> photo = new ArrayList<>();//基金宣传图片 FundVideoOrPhoto
	private boolean platform;// 是否是平台基金

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

	public String getDescription(){
		return description;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getPoster(){
		return poster;
	}

	public void setPoster(String poster){
		this.poster = poster;
	}

	public String getSlogan(){
		return slogan;
	}

	public void setSlogan(String slogan){
		this.slogan = slogan;
	}

	public String getCooperationUnit(){
		return cooperationUnit;
	}

	public void setCooperationUnit(String cooperationUnit){
		this.cooperationUnit = cooperationUnit;
	}

	public String getCooperationUrl(){
		return cooperationUrl;
	}

	public void setCooperationUrl(String cooperationUrl){
		this.cooperationUrl = cooperationUrl;
	}

	public int getReleaseForm(){
		return releaseForm;
	}

	public void setReleaseForm(int releaseForm){
		this.releaseForm = releaseForm;
	}

	public int getPreBalanceDay(){
		return preBalanceDay;
	}

	public void setPreBalanceDay(int preBalanceDay){
		this.preBalanceDay = preBalanceDay;
	}

	public double getAmount(){
		return amount;
	}

	public void setAmount(double amount){
		this.amount = amount;
	}

	public int getWeekType(){
		return weekType;
	}

	public void setWeekType(int weekType){
		this.weekType = weekType;
	}

	public int getWeek(){
		return week;
	}

	public void setWeek(int week){
		this.week = week;
	}

	public double getDeposit(){
		return deposit;
	}

	public void setDeposit(double deposit){
		this.deposit = deposit;
	}

	public boolean isPlatform(){
		return platform;
	}

	public void setPlatform(boolean platform){
		this.platform = platform;
	}
}
