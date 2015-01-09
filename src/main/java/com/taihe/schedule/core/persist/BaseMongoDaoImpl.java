package com.taihe.schedule.core.persist;

import java.util.List;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

/** 
 * @Deprecated 统一使用MogonPersistenceImpl
 * @author yangt 
 * @date 2014年9月25日 下午8:43:18 
 * @param <T> 
 */
@Deprecated
public abstract class BaseMongoDaoImpl<T> implements BaseMongoDao<T> {
      
    /** 
     * spring mongodb　集成操作类　 
     */  
    protected MongoTemplate mongoTemplate;  
  
    @Override  
    public List<T> find(Query query) {  
        return mongoTemplate.find(query, this.getEntityClass());  
    }  
  
    @Override  
    public T findOne(Query query) {  
        return mongoTemplate.findOne(query, this.getEntityClass());  
    }  
  
    @Override  
    public void update(Query query, Update update) {  
        mongoTemplate.findAndModify(query, update, this.getEntityClass());  
    }  
  
  
    @Override  
    public T findById(String id) {  
        return mongoTemplate.findById(id, this.getEntityClass());  
    }  
  
    @Override  
    public T findById(String id, String collectionName) {  
        return mongoTemplate.findById(id, this.getEntityClass(), collectionName);  
    }  
      
      
    @Override  
    public long count(Query query){  
        return mongoTemplate.count(query, this.getEntityClass());  
    }  
    
    public boolean remove(Query query) {
    	mongoTemplate.remove(query, this.getEntityClass());
    	return true;
    }
    
    /** 
     * 获取需要操作的实体类class 
     *  
     * @return 
     */  
    protected abstract Class<T> getEntityClass();
  
    /** 
     * 注入mongodbTemplate 
     *  
     * @param mongoTemplate 
     */  
    protected abstract void setMongoTemplate(MongoTemplate mongoTemplate);  
}
