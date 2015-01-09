package com.taihe.schedule.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.taihe.schedule.service.MsgSendService;
import com.taihe.schedule.util.HttpRequest;


public class MsgSendServiceImpl implements MsgSendService {
	
	final static Logger logger = LoggerFactory.getLogger(MsgSendServiceImpl.class);
	
	private String msgSendUrl;
	private String msgTargetEmail;
	private static final String MSG_PREFIX = "{\"destinations\": { \"E\":\"";
	private static final String MSG_MID = "\"},\"strategy\": \"001\",\"sender\": \"定时调度中心\",\"client\": \"12342134\",\"msgdata\": {\"USE_AS_SOURCE\": {\"title\":\"活动取消提醒\",\"description\":\"";
	private static final String MSG_TAIL = "\"}}}"; 
	public void sendEmailForCancelAct(String msg){
		StringBuilder sendMsg = new StringBuilder();
		sendMsg.append(MSG_PREFIX);
		sendMsg.append(msgTargetEmail);
		sendMsg.append(MSG_MID);
		sendMsg.append(msg);
		sendMsg.append(MSG_TAIL);
		
		HttpRequest httpRequest = new HttpRequest();
    	String resultInfo = httpRequest.Post(msgSendUrl)
	             .addFormData("data",sendMsg.toString())
	             .execute()
	             .returnContent();
    	logger.debug("发送消息，返回参数为："+resultInfo);
	}
	
	public String getMsgTargetEmail(){
		return msgTargetEmail;
	}
	public void setMsgTargetEmail(String msgTargetEmail){
		this.msgTargetEmail = msgTargetEmail;
	}
	public String getMsgSendUrl(){
		return msgSendUrl;
	}
	public void setMsgSendUrl(String msgSendUrl){
		this.msgSendUrl = msgSendUrl;
	}
}
