package com.taihe.schedule.core.persist;

import java.util.List;

import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;


public interface MogonPersistence {
	
	public <T> T save(T objectToSave,String collectionName);
	
	public <T> T save(T objectToSave);
	
    public <T> List<T> find(Query query,Class<T> t);
  
    public <T> T findOne(Query query,Class<T> t);
  
    public <T> void update(Query query, Update update,Class<T> t);
  
    public <T> T findById(String id,Class<T> t);  
  
    public <T> T findById(String id, String collectionName,Class<T> t);
    
    public <T> long count(Query query,Class<T> t) ;
    
    public <T> boolean remove(Query query,Class<T> t);
}
