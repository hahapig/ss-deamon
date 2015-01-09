package com.hahapig.schedule.core.dynamic;

import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.support.PersistenceExceptionTranslator;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoExceptionTranslator;
import org.springframework.util.Assert;

import com.mongodb.DB;


/** 
 * 动态切换数据源到不同的mongoDB
 * @author yangt 
 * @date 2014年9月2日 下午2:07:32 
 *  
 */
public class DynamicMongoFactory implements MongoDbFactory{
	
	private MongoExceptionTranslator exceptionTranslator;
	
	private Map<Object,MongoDbFactory> factoryMap;
	
	private MongoDbFactory defaultMongoDbFactory;
	
	
	public void setDefaultMongoDbFactory(MongoDbFactory defaultMongoDbFactory){
		this.defaultMongoDbFactory = defaultMongoDbFactory;
	}

	@Override
	public final DB getDb() throws DataAccessException{
		return determineTargetDataSource().getDb();
	}
	
	protected MongoDbFactory determineTargetDataSource() {
		Assert.notNull(factoryMap, "DynamicMongoFactory.factory not initialized");
		Object lookupKey = determineCurrentLookupKey();
		MongoDbFactory dbFactory = this.factoryMap.get(lookupKey);
		if (dbFactory == null && (lookupKey == null)) {
			dbFactory = this.defaultMongoDbFactory;
		}
		if (dbFactory == null) {
			throw new IllegalStateException("请检查注解DataSource，没有数据源 [" + lookupKey + "]");
		}
		return dbFactory;
	}
	
	protected Object determineCurrentLookupKey(){
		return DataSourceContextHolder.getDataSourceType();
	} 
	
	@Deprecated
	@Override
	public final DB getDb(String dbName) throws DataAccessException{
		return determineTargetDataSource().getDb(dbName);
	}

	@Override
	public PersistenceExceptionTranslator getExceptionTranslator(){
		if(exceptionTranslator==null){
			this.exceptionTranslator = new MongoExceptionTranslator();
		}
		return this.exceptionTranslator;
	}

	public void setFactoryMap(Map<Object,MongoDbFactory> factoryMap){
		this.factoryMap = factoryMap;
	}
}
