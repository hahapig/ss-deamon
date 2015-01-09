package com.taihe.schedule.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.taihe.schedule.entity.TicketCheked;

/**
 * 流工具类
 * */
public abstract class StreamUtil {
	private static File cacheFile;
	static Logger log=Logger.getLogger(StreamUtil.class);
	public static List<String> fileNameCache=new ArrayList<String>();
	static{
		log.info("init..... >>>>>>>>>>>>>>>>>>>>>>>>>>>>>> 读取处理过的验票文件 ");
		cacheFile=new File("/var/showstart/eticket/readcache/recordFlg.txt");//放已处理过的文件
		try {//每次宕机后读取当前处理过的文件
			StringBuffer sb = new StringBuffer("");
			FileReader fr = new FileReader(cacheFile);
			BufferedReader br = new BufferedReader(fr);
		    String line = null;  
	        while((line = br.readLine()) != null){  
	        	sb.append(line);  
	        } 
		    br.close();
		    fr.close();
		    String[] temp=sb.toString().split(",");
		    for (String str : temp) {
		    	fileNameCache.add(str);//初始化缓存记录
			}
		    log.info("init.ok... >>>>>>>>>>>>>>>>>>>>>>>>>>>>>> 初始化缓存成功 ");
		} catch (Exception e) {
			log.error("读取处理过的验票文件失败，recordFlg.txt文件尚未初始化......",e);
		}
	}
	
	/**
	 * 获取需要操作的文件
	 * */
	public static List<File> getFile(){
		List<File> lists=new ArrayList<File>();
		File[] files=new File("/var/showstart/eticket/feedback").listFiles();
		for (File f : files) {
			if(!fileNameCache.contains(f.getName())){
				lists.add(f);
			}
		}
		return lists;
	}
	
	/**
	 * 获取某个文件需要更新的验票信息
	 * */
	public static List<TicketCheked> getTickeds(File file){
		log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>> 获取某个文件需要更新的验票信息 ，当前文件："+file.getName());
		try {
			StringBuffer sb = new StringBuffer();
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
		    String line = null;  
	        while((line = br.readLine()) != null){  
	        	sb.append(line);  
	        }
		    br.close();
		    fr.close();
		    return JSON.parseArray(sb.toString(),TicketCheked.class);
		} catch (Exception e) {
			log.error("获取某个文件需要更新的验票信息失败 ，当前文件:"+file.getName(),e);
			return new ArrayList<TicketCheked>();
		}
	}
	
	/**
	 * 读取当前处理过的文件
	 * */
	/*public static void readCache(){
		log.info("init.....>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> 读取处理过的验票文件 ");
		try {
			StringBuffer sb = new StringBuffer();
			FileReader fr = new FileReader(cacheFile);
		    BufferedReader br = new BufferedReader(fr);
		    String line = null;  
	        while((line = br.readLine()) != null){  
	        	sb.append(line);  
	        }
		    br.close();
		    fr.close();
		    String[] temp=sb.toString().split(",");
		    for (String str : temp) {
		    	fileNameCache.add(str);
			}
		    log.info("init...ok...>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> 初始化缓存成功 ");
		} catch (Exception e) {
			log.error("读取处理过的验票文件失败，recordFlg.txt文件尚未初始化......",e);
		}
	}*/
	
	/**
	 * 保存当前处理过的文件
	 * */
	public static void saveCache(){
		log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>...同步处理过的验票文件文件名称到本地 ");
		StringBuffer sb = new StringBuffer();
		for (String str : fileNameCache) {
			sb.append(str).append(",");
		}
	    try {
	    	FileWriter fw = new FileWriter(cacheFile);
	    	BufferedWriter bw = new BufferedWriter(fw);
		    bw.write( sb.toString());
		    bw.flush();
		    bw.close();
		    fw.close();
		} catch (IOException e) {
			log.error("同步处理过的验票文件名称失败...",e);
		}  
	}
	
	/**
	 * 读取接口配置文件
	 * */
	public static String getUrls(String name){
		 Properties properties=null;
		 if(properties==null){
			 properties=new Properties();
			 try {
					FileInputStream in = new FileInputStream(Constants.PROPERTIESURL);
					properties.load(in);
				} catch (IOException e) {
					log.info("StreamUtil.getUrls(..)  读取接口地址配置文件失败......"+e);
					throw new RuntimeException("读取配置文件错误");
				}
		 }
		 return properties.getProperty(name);
    }
}
