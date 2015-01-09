package com.taihe.schedule.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Dong Wang.
 * Created  on 2014/7/14 12:02.
 * 自定义事件以及开放预约
 * <p/>
 * 认证后，插入艺人场地半年开放数据
 * 开放时间必须 > 90分钟
 */
public class CalendarEvent{
    private String id;
    private String userId;//用户数据库ID
    private String title;//活动标题
    private Date start;//定位到某一天某个时刻 开始
    private Date end;//定位到某一天某个时刻 结束
    private Date day;//具体某天 精确到天
    private int userType;//用户分类
    private int type;//分类：采访、排练、演唱会、【预约】
    private int preDays = 0;//提前多少天提醒
    private boolean alarm = false;//闹钟开关
    private List<Integer> repeat = new ArrayList<>();//循坏周数
    private String multiId;//循环事件列表id
	
	public String getId(){
		return id;
	}
	
	public void setId(String id){
		this.id = id;
	}
	
	public String getUserId(){
		return userId;
	}
	
	public void setUserId(String userId){
		this.userId = userId;
	}
	
	public String getTitle(){
		return title;
	}
	
	public void setTitle(String title){
		this.title = title;
	}
	
	public Date getStart(){
		return start;
	}
	
	public void setStart(Date start){
		this.start = start;
	}
	
	public Date getEnd(){
		return end;
	}
	
	public void setEnd(Date end){
		this.end = end;
	}
	
	public Date getDay(){
		return day;
	}
	
	public void setDay(Date day){
		this.day = day;
	}
	
	public int getUserType(){
		return userType;
	}
	
	public void setUserType(int userType){
		this.userType = userType;
	}
	
	public int getType(){
		return type;
	}
	
	public void setType(int type){
		this.type = type;
	}
	
	public int getPreDays(){
		return preDays;
	}
	
	public void setPreDays(int preDays){
		this.preDays = preDays;
	}
	
	public boolean isAlarm(){
		return alarm;
	}
	
	public void setAlarm(boolean alarm){
		this.alarm = alarm;
	}
	
	public List<Integer> getRepeat(){
		return repeat;
	}
	
	public void setRepeat(List<Integer> repeat){
		this.repeat = repeat;
	}
	
	public String getMultiId(){
		return multiId;
	}
	
	public void setMultiId(String multiId){
		this.multiId = multiId;
	}
    
}
