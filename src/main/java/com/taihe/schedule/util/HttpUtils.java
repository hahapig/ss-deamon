package com.taihe.schedule.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;

/**
 * @author Xing Li
 * 2014-11-28 17:12
 * 封装一些实际工作中使用的HTTP请求工具
 * */
public abstract class HttpUtils {
	private static final Logger log=Logger.getLogger(HttpUtils.class);
	/**
	 * GET请求
	 * @return <T> T
	 * */
	public static <T>T GETObject(String url,boolean isArray,Class<T> clazz){
		return JSONObject.parseObject(GETJSONObject(url,isArray).toString(),clazz);
	}
	/**
	 * GET请求
	 * @return JSONObject
	 * */
	public static JSONObject GETJSONObject(String url,boolean isArray){
		JSONObject result=null;
		try {
			if(isArray){
				result=(JSONObject) JSONObject.parseObject(GET(url)).getJSONArray("result").get(0);
			}else{
				result=JSONObject.parseObject(GET(url)).getJSONObject("result");
			}
		} catch (Exception e) {
			log.error("HttpUtils.GETJSONObject(...) 解析结果异常",e);
			log.debug(GET(url));
		}
		return result;
	}
	
	/**
	 * GET请求
	 * @return String
	 * */
	public static String GET(String url){
		return streamToString(GETStream(url));
	}
	
	/**
	 * GET请求
	 * @return InputStream
	 * */
	public static InputStream GETStream(String url){
		try {
			return GETHttpResponse(url).getEntity().getContent();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} 
	}
	/**
	 * GET请求
	 * @return HttpResponse
	 * */
	public static HttpResponse GETHttpResponse(String url){
		HttpClient client=HttpClients.createDefault();
		HttpUriRequest request=new HttpGet(url);
		HttpResponse resp;
		log.info("HttpClient发送请求： " + request.getURI());
		try {
			resp = client.execute(request);
			return resp;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} 
	}
	
	/**
	 *POST请求
	 * @return <T> T
	 * */
	public static <T>T POSTObject(String url,Map<String,Object> pairs,boolean isArray,Class<T> clazz){
		return JSONObject.parseObject(POSTJSONObject(url,pairs,isArray).toString(),clazz);
	}
	
	/**
	 * POST请求
	 * @return JSONObject
	 * */
	public static JSONObject POSTJSONObject(String url,Map<String,Object> pairs,boolean isArray){
		JSONObject result=null;
		try {
			if(isArray){
				result=(JSONObject) JSONObject.parseObject(POST(url,pairs)).getJSONArray("result").get(0);
			}else{
				result=JSONObject.parseObject(POST(url,pairs)).getJSONObject("result");
			}
		} catch (Exception e) {
			log.error("HttpUtils.POSTJSONObject(...) 解析结果异常",e);
			log.debug(POST(url,pairs));
		}
		return result;
	}
	
	/**
	 * POST请求
	 * @return String
	 * */
	public static String POST(String url,Map<String,Object> pairs){
		return streamToString(POSTStream(url,pairs));
	}
	/**
	 * POST请求
	 * @return InputStream
	 * */
	public static InputStream POSTStream(String url,Map<String,Object> pairs){
		try {
			return POSTHttpResponse(url,pairs).getEntity().getContent();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} 
	}
	
	/**
	 * POST请求
	 * @return HttpResponse
	 * */
	public static HttpResponse POSTHttpResponse(String url,Map<String,Object> pairs){
		List<NameValuePair> formData=new ArrayList<NameValuePair>();
		HttpClient client=HttpClients.createDefault();
		HttpUriRequest request=new HttpPost(url);
		
		Set<String> keys=pairs.keySet();
		for (String str : keys) {
			formData.add(new BasicNameValuePair(str,pairs.get(str).toString()));
		}
		
		request.setHeader(HTTP.CONTENT_ENCODING, "UTF-8");
		HttpResponse resp;
		log.info("HttpClient发送请求： " + request.getURI() + " [POST]");
		try {
			((HttpPost)request).setEntity(new UrlEncodedFormEntity(formData,"UTF-8"));
			resp = client.execute(request);
			return resp;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} 
	}
	
	/**
	 * 输入流转换成字符串
	 * @return String
	 * */
	private static String streamToString(InputStream in){
        if (in!= null){
            try{
                BufferedReader bufferReader = new BufferedReader(new InputStreamReader(in));
                StringBuffer buffer = new StringBuffer();
                String line = new String();
                while ((line = bufferReader.readLine())!= null){
                    buffer.append(line);
                }
                return buffer.toString();
            }catch (Exception e){
            	throw new RuntimeException(e);
            }
        }
        return null;
    }
}
