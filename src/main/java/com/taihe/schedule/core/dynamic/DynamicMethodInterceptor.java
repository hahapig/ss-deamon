package com.taihe.schedule.core.dynamic;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.stereotype.Service;

/** 
 * 动态数据源切换拦截器
 * @author yangt 
 * @date 2014年9月2日 下午2:12:16 
 *  
 */
@Service("dynamicMethodInterceptor")
public class DynamicMethodInterceptor implements MethodInterceptor{

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable{
		Object target = invocation.getThis();
		if(target.getClass().isAnnotationPresent(DataSource.class)){
			DataSource dataSource = target.getClass().getAnnotation(DataSource.class);
			String key = dataSource.value();
			//提前清理一次
			DataSourceContextHolder.clearDataSourceType();
			DataSourceContextHolder.setDataSourceType(key);
			Object returnInfo = invocation.proceed();
			//可能存在线程池，最后一定要清理
			DataSourceContextHolder.clearDataSourceType();
			return returnInfo;
		}
		return invocation.proceed();
	}
}
