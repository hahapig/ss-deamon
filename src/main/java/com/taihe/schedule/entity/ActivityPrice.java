package com.taihe.schedule.entity;


/**
 * Created by Dong Wang.
 * Created on 2014/8/30 14:46.
 * 活动价格
 * 1、住宿 300/双人间（艺人）
 * 2、机票 <=500(Km) 500/人   <=1000（Km）  1000/人   >1000（Km）1500/人（艺人）
 * （3+4+5）= 总酬金
 * 3、（场地酬金+艺人酬金）占总酬金70%
 * 4、执行团队佣金 占总酬金20%
 * 5、平台佣金 占总酬金10%
 */
public class ActivityPrice {
    private double activity;//活动价格
    private double performer;//艺人价格
    private double site;//场地价格
    private double actuator;//执行团队
    private double platform;//平台佣金
    private double hotel;//住宿酒店价格
    private double airplane;//机票价格
	
	public double getActivity(){
		return activity;
	}
	
	public void setActivity(double activity){
		this.activity = activity;
	}
	
	public double getPerformer(){
		return performer;
	}
	
	public void setPerformer(double performer){
		this.performer = performer;
	}
	
	public double getSite(){
		return site;
	}
	
	public void setSite(double site){
		this.site = site;
	}
	
	public double getActuator(){
		return actuator;
	}
	
	public void setActuator(double actuator){
		this.actuator = actuator;
	}
	
	public double getPlatform(){
		return platform;
	}
	
	public void setPlatform(double platform){
		this.platform = platform;
	}
	
	public double getHotel(){
		return hotel;
	}
	
	public void setHotel(double hotel){
		this.hotel = hotel;
	}
	
	public double getAirplane(){
		return airplane;
	}
	
	public void setAirplane(double airplane){
		this.airplane = airplane;
	}

	@Override
	public String toString() {
		return "ActivityPrice [activity=" + activity + ", performer="
				+ performer + ", site=" + site + ", actuator=" + actuator
				+ ", platform=" + platform + ", hotel=" + hotel + ", airplane="
				+ airplane + "]";
	}
}
