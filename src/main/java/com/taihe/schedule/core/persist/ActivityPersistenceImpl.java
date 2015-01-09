package com.taihe.schedule.core.persist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.taihe.schedule.entity.Activity;

/** 
 * @Deprecated 统一使用MogonPersistenceImpl
 * @author yangt 
 * @date 2014年9月25日 下午8:41:58 
 *  
 */
@Deprecated
@Repository
public class ActivityPersistenceImpl extends BaseMongoDaoImpl<Activity>{

	@Override
	protected Class<Activity> getEntityClass(){
		return Activity.class;
	}
	
	@Autowired
	@Qualifier("activityMongoTemplate")
	@Override
	protected void setMongoTemplate(MongoTemplate mongoTemplate){
		this.mongoTemplate = mongoTemplate;
	}
}
