package com.taihe.schedule.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.taihe.schedule.util.Constants;

/**
 * Created by Dong Wang.
 * Created on 14-4-16 下午4:00.
 * 艺人或经纪人
 */
public class Performer {
    private UserInfo userInfo;//公共信息
    private int type = 1;//艺人形式 1:个人 2:团体
    private String description;//简介
    private String notice;//公告
    private Date birthday = new Date();//艺人生日或者团队组建日
    private String nativePlace;//籍贯 所属地
    private List<Integer> style = new ArrayList<>(Arrays.asList(Constants.POP));//音乐风格
    private String company;//代理公司
    private List<String> poster = new ArrayList<>();//首页轮播海报
    private int showType = Constants.SHOW_TYPE_ACCOMPANIMENT;//表演形式,伴奏带，现场乐队
    private long contact;//其他联系方式
    private double price = 2000d;//演出酬金,演出目前只有90-150+
    private int count = 1;//人数
    private double longitude = 116.3d;//所在地经度 默认北京
    private double latitude = 39.9d;//所在地纬度
    private String companyLogo;//代理公司LOGO地址
    private int checked = Constants.USER_CHECK_ING;//是否通过核实 1认证中 2认证成功 3认证失败
	
	public UserInfo getUserInfo(){
		return userInfo;
	}
	
	public void setUserInfo(UserInfo userInfo){
		this.userInfo = userInfo;
	}
	
	public int getType(){
		return type;
	}
	
	public void setType(int type){
		this.type = type;
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
	
	public Date getBirthday(){
		return birthday;
	}
	
	public void setBirthday(Date birthday){
		this.birthday = birthday;
	}
	
	public String getNativePlace(){
		return nativePlace;
	}
	
	public void setNativePlace(String nativePlace){
		this.nativePlace = nativePlace;
	}
	
	public List<Integer> getStyle(){
		return style;
	}
	
	public void setStyle(List<Integer> style){
		this.style = style;
	}
	
	public String getCompany(){
		return company;
	}
	
	public void setCompany(String company){
		this.company = company;
	}
	
	public List<String> getPoster(){
		return poster;
	}
	
	public void setPoster(List<String> poster){
		this.poster = poster;
	}
	
	public int getShowType(){
		return showType;
	}
	
	public void setShowType(int showType){
		this.showType = showType;
	}
	
	public long getContact(){
		return contact;
	}
	
	public void setContact(long contact){
		this.contact = contact;
	}
	
	public double getPrice(){
		return price;
	}
	
	public void setPrice(double price){
		this.price = price;
	}
	
	public int getCount(){
		return count;
	}
	
	public void setCount(int count){
		this.count = count;
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
	
	public String getCompanyLogo(){
		return companyLogo;
	}
	
	public void setCompanyLogo(String companyLogo){
		this.companyLogo = companyLogo;
	}
	
	public int getChecked(){
		return checked;
	}
	
	public void setChecked(int checked){
		this.checked = checked;
	}

}
