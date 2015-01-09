package com.hahapig.schedule.core.utils;

import java.util.HashMap;
import java.util.Map;

public class ConfigReader {
	private Map<String, String> configMap = new HashMap<String, String>();
	
	
	public Map<String, String> getConfigMap(){
		return configMap;
	}

	
	public void setConfigMap(Map<String, String> configMap){
		this.configMap = configMap;
	}

	public String getCronExpression(String key){
		return configMap.get(key);
	}
}
