package com.taihe.schedule.core.persist;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.mongodb.WriteResult;

@Service
public class MogonPersistenceImpl implements MogonPersistence{
	
	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public <T> List<T> find(Query query, Class<T> t){
		return mongoTemplate.find(query, t);  
	}

	@Override
	public <T> T findOne(Query query, Class<T> t){
		return mongoTemplate.findOne(query, t);  
	}

	@Override
	public <T> void update(Query query, Update update, Class<T> t){
		mongoTemplate.findAndModify(query, update, t);  
	}

	@Override
	public <T> T findById(String id, Class<T> t){
		return mongoTemplate.findById(id, t);  
	}

	@Override
	public <T> T findById(String id, String collectionName, Class<T> t){
		return mongoTemplate.findById(id, t, collectionName);  
	}

	@Override
	public <T> long count(Query query, Class<T> t){
		return mongoTemplate.count(query, t);  
	}

	@Override
	public <T> boolean remove(Query query, Class<T> t){
		WriteResult result = mongoTemplate.remove(query, t);
		return result.getN()>0;
	}

	@Override
	public <T> T save(T objectToSave){
		mongoTemplate.save(objectToSave);
		return objectToSave;
	}
	
	@Override
	public <T> T save(T objectToSave,String collectionName){
		mongoTemplate.save(objectToSave,collectionName);
		return objectToSave;
	}
}
