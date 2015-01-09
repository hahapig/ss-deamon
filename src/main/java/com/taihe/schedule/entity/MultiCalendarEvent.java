package com.taihe.schedule.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Dong Wang.
 * Created  on 2014/7/17 9:43.
 * 批量操作记录
 */
public class MultiCalendarEvent {
    private String objectId;
    private String userId;//用户数据库ID
    private String title;//活动标题
    private Date start;//定位到某一天某个时刻 开始
    private Date end;//定位到某一天某个时刻 结束
    private List<Integer> repeat = new ArrayList<>();//重复类型
    private int type;//分类：采访、排练、演唱会、【预约】
    private int preDays = 0;//提前多少天提醒
    private boolean on = true;//预约开关，true为开启，false为关闭
    private boolean alarm = false;//闹钟开关
    private int kind;//1 事件循环 2 预约循环
	
	public String getObjectId(){
		return objectId;
	}
	
	public void setObjectId(String objectId){
		this.objectId = objectId;
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
	
	public List<Integer> getRepeat(){
		return repeat;
	}
	
	public void setRepeat(List<Integer> repeat){
		this.repeat = repeat;
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
	
	public boolean isOn(){
		return on;
	}
	
	public void setOn(boolean on){
		this.on = on;
	}
	
	public boolean isAlarm(){
		return alarm;
	}
	
	public void setAlarm(boolean alarm){
		this.alarm = alarm;
	}
	
	public int getKind(){
		return kind;
	}
	
	public void setKind(int kind){
		this.kind = kind;
	}
    
}
