package com.taihe.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;


public class StartScheduleSys {
	
	final static Logger logger = LoggerFactory.getLogger(StartScheduleSys.class);
	public static void main(String[] args){
		System.out.println(StartScheduleSys.class.getResource("").toString());
		logger.debug("开始启动容器");
		@SuppressWarnings("resource")
		ApplicationContext context = new FileSystemXmlApplicationContext("classpath:spring/applicationContext.xml");
		logger.debug("成功启动容器"+context.getId());
	}
}
