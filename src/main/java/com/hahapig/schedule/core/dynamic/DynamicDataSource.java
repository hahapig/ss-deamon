package com.hahapig.schedule.core.dynamic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;


/** 
 * MySQL动态数据源
 * @author yangt 
 * @date 2014年9月2日 下午2:12:58 
 *  
 */
public class DynamicDataSource extends AbstractRoutingDataSource{
	final static Logger logger = LoggerFactory.getLogger(DynamicDataSource.class);
	@Override
	protected Object determineCurrentLookupKey(){
		return DataSourceContextHolder.getDataSourceType();
	}
}
