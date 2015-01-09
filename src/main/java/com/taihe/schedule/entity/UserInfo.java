package com.taihe.schedule.entity;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Field;
public class UserInfo {
	
	@Field("id")
	private String userId;
	
	@Deprecated //此字段名慎重使用 
	private String id;
    private String name;//名称
    private String alias;//其他名称
    private List<String> avatar = new ArrayList<>();//头像地址
    private int userType;//用户类型 1粉丝 2艺人 3场地 4赞助商 5主办方
    private long tel;//手机号码
    private String mail;//邮箱地址
    private String web;
    private int sex;//性别 1男 2女 3其他
    private String bindWB;//绑定微博账号
    private String bindQQ;//绑定QQ账号

    public String getUserId(){
		return userId;
	}
	public void setUserId(String userId){
		this.userId = userId;
	}
    
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

	
	public String getAlias(){
		return alias;
	}

	
	public void setAlias(String alias){
		this.alias = alias;
	}

	
	public List<String> getAvatar(){
		return avatar;
	}

	
	public void setAvatar(List<String> avatar){
		this.avatar = avatar;
	}

	
	public int getUserType(){
		return userType;
	}

	
	public void setUserType(int userType){
		this.userType = userType;
	}

	
	public long getTel(){
		return tel;
	}

	
	public void setTel(long tel){
		this.tel = tel;
	}

	
	public String getMail(){
		return mail;
	}

	
	public void setMail(String mail){
		this.mail = mail;
	}

	
	public String getWeb(){
		return web;
	}

	
	public void setWeb(String web){
		this.web = web;
	}

	
	public int getSex(){
		return sex;
	}

	
	public void setSex(int sex){
		this.sex = sex;
	}

	
	public String getBindWB(){
		return bindWB;
	}

	
	public void setBindWB(String bindWB){
		this.bindWB = bindWB;
	}

	
	public String getBindQQ(){
		return bindQQ;
	}

	
	public void setBindQQ(String bindQQ){
		this.bindQQ = bindQQ;
	}

	public String oneAvatar() {
        return avatar.size() > 0 ? avatar.get(0) : "";
    }
}
