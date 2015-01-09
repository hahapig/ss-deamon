package com.hahapig.schedule.core.support.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * @Description 功能参加quartz注解@PersistJobDataAfterExecution</p>
 * 此注解还未经测试
 * @author yangt 
 * @date 2014年8月21日 上午10:57:11 
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface ExtendPersistJobDataAfterExecution {
}
