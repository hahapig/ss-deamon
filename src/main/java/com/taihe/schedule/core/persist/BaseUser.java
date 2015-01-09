package com.taihe.schedule.core.persist;

import org.springframework.data.mongodb.core.mapping.Field;


public class BaseUser {
	
	@Field("id")
	private String userId;
	@Deprecated //此字段慎重使用 
	private String id;
    private String name;//名字
    private String avatar;//头像
    private int type;//用户类型
    
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
	
	public String getAvatar(){
		return avatar;
	}
	
	public void setAvatar(String avatar){
		this.avatar = avatar;
	}
	
	public int getType(){
		return type;
	}
	
	public void setType(int type){
		this.type = type;
	}

	public String getUserId(){
		return userId;
	}

	public void setUserId(String userId){
		this.userId = userId;
	}
}
