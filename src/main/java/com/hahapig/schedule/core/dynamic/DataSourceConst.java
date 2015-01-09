package com.hahapig.schedule.core.dynamic;


public abstract class DataSourceConst {
	//-----Mysql
	public static final String MYSQL_DEFAULT_DB = "dataSource.default";
	public static final String MYSQL_ORDER_DB="orderDataSource";  
	public static final String MYSQL_STOCK_DB="stockDataSource";  
	
	//mongodb
	public static final String MONGO_DEFAULT_DB="activityDataSource";
	public static final String MONGO_ACTIVITY_DB="activityDataSource";
	public static final String MONGO_CALENDER_DB="calenderDataSource";  
	public static final String MONGO_INFO_DB="infoDataSource";  
}

