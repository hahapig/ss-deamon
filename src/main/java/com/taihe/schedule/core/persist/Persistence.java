package com.taihe.schedule.core.persist;

import java.util.List;

/**
 * 
 * 定义数据访问层, 访问MYSQL
 */
public interface Persistence {
	
	/** 
	 * 插入记录
	 */
	<T> int insert(String statement,T t);
	
	/** 
	 * 删除
	 */
	int delete(String statement,Object params);
	 
	int update(String statement,Object params);
	
	<T> List<T> selectList(String statement,Object params,Class<T> type);
	
	<T> T selectObject(String statement,Object params,Class<T> type);
	
	<T> int insert(String statement,List<T> object);
	
	<T> int bacthUpdate(String statement,List<T> object);
}
