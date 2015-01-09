package com.hahapig.schedule.core.support.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @Description jobGroup(默认为类名)指定任务分组，</p>
 * jobName指定任务名称(默认为方法名)，</p>
 * cronExpression(cornexpression优先级为---类注解-小于-方法注解--小于-->配置)
 * @author yangt 
 * @date 2014年8月20日 上午11:55:42 
 *
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface ScheduleAnnotation {
	String jobGroup() default "";
	String jobName() default "";
	String cronExpression() default "";
	String description() default "";
	boolean replaceExsitedJob() default false;
}
