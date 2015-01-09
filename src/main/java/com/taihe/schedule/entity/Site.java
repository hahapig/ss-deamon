package com.taihe.schedule.entity;

import java.util.ArrayList;
import java.util.List;

import com.taihe.schedule.util.Constants;

/**
 * Created by Dong Wang.
 * Created on 2014/7/18 11:12.
 * 场地信息
 */
public class Site {
    private UserInfo userInfo;//公共信息
    private String address;//场地地址
    private int count = 500;//场地容量，可容纳人数
    private double holidayPrice = 2000d;//节假日场租
    private double normalPrice = 2000d;//普通日场租
    private boolean approval = false;//是否报批
    private String description;//简介
    private String notice;//公告
    private List<String> poster = new ArrayList<>();//首页轮播海报
    private double longitude = 116.3d;//所在地经度 默认北京
    private double latitude = 39.9d;//所在地纬度

    private long contact;//其他联系方式
    private String contactName;//联系人

    private int checked = Constants.USER_CHECK_ING;//是否通过核实 1认证中 2认证成功 3认证失败

    public String onePoster() {
        return poster.size() > 0 ? poster.get(0) : "";
    }
	
	public UserInfo getUserInfo(){
		return userInfo;
	}
	
	public void setUserInfo(UserInfo userInfo){
		this.userInfo = userInfo;
	}
	
	public String getAddress(){
		return address;
	}

	public void setAddress(String address){
		this.address = address;
	}
	
	public int getCount(){
		return count;
	}
	
	public void setCount(int count){
		this.count = count;
	}
	
	public double getHolidayPrice(){
		return holidayPrice;
	}

	public void setHolidayPrice(double holidayPrice){
		this.holidayPrice = holidayPrice;
	}
	
	public double getNormalPrice(){
		return normalPrice;
	}
	
	public void setNormalPrice(double normalPrice){
		this.normalPrice = normalPrice;
	}

	public boolean isApproval(){
		return approval;
	}

	public void setApproval(boolean approval){
		this.approval = approval;
	}

	
	public String getDescription(){
		return description;
	}
	
	public void setDescription(String description){
		this.description = description;
	}

	public String getNotice(){
		return notice;
	}

	public void setNotice(String notice){
		this.notice = notice;
	}

	public List<String> getPoster(){
		return poster;
	}

	public void setPoster(List<String> poster){
		this.poster = poster;
	}

	
	public double getLongitude(){
		return longitude;
	}

	public void setLongitude(double longitude){
		this.longitude = longitude;
	}
	
	public double getLatitude(){
		return latitude;
	}

	public void setLatitude(double latitude){
		this.latitude = latitude;
	}
	
	public long getContact(){
		return contact;
	}
	
	public void setContact(long contact){
		this.contact = contact;
	}

	public String getContactName(){
		return contactName;
	}

	public void setContactName(String contactName){
		this.contactName = contactName;
	}
	
	public int getChecked(){
		return checked;
	}

	public void setChecked(int checked){
		this.checked = checked;
	}
    
}
