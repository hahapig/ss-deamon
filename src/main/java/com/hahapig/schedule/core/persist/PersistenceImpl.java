package com.hahapig.schedule.core.persist;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersistenceImpl implements Persistence{
	
	@Autowired
	private org.mybatis.spring.SqlSessionTemplate sessionTemplate;
	
	@Transactional(propagation=Propagation.REQUIRED)
	public <T> int insert(String statement,T t){
		return sessionTemplate.insert(statement, t);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public int delete(String statement,Object params){
		return sessionTemplate.delete(statement, params);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public int update(String statement, Object params){
		 return sessionTemplate.update(statement, params);
	}
	
	@Transactional(propagation=Propagation.SUPPORTS)
	public <T> T selectObject(String statement,Object params,Class<T> type){
		return sessionTemplate.selectOne(statement, params);
	}
	
	@Transactional(propagation=Propagation.SUPPORTS)
	public <T> List<T> selectList(String statement,Object params,Class<T> type){
		return sessionTemplate.selectList(statement, params);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public <T> int insert(String statement,List<T> object){
		int count = 0;
		for (T t : object) {
			count = count+sessionTemplate.insert(statement, t);
		}
		return count;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public <T> int bacthUpdate(String statement, List<T> object) {
		int count = 0;
		for (T t : object) {
			count = count+sessionTemplate.update(statement, t);
		}
		return count;
	}
}
