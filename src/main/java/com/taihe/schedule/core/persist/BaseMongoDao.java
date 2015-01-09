package com.taihe.schedule.core.persist;

import java.util.List;

import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

/** 
 * @Deprecated 统一使用MogonPersistence
 * @author yangt 
 * @date 2014年9月25日 下午8:43:35 
 */
@Deprecated
public interface BaseMongoDao<T> {
	
	/** 
     * 通过条件查询实体(集合) 
     *  
     * @param query 
     */  
    public List<T> find(Query query) ;  
  
    /** 
     * 通过一定的条件查询一个实体 
     *  
     * @param query 
     * @return 
     */  
    public T findOne(Query query) ;  
  
    /** 
     * 通过条件查询更新数据 
     *  
     * @param query 
     * @param update 
     * @return 
     */  
    public void update(Query query, Update update) ;  
  
    /** 
     * 通过ID获取记录 
     *  
     * @param id 
     * @return 
     */  
    public T findById(String id) ;  
  
    /** 
     * 通过ID获取记录,并且指定了集合名(表的意思) 
     *  
     * @param id 
     * @param collectionName 
     *            集合名 
     * @return 
     */  
    public T findById(String id, String collectionName) ;  
      
      
    /** 
     * 求数据总和 
     * @param query 
     * @return 
     */  
    public long count(Query query); 
    
    /** 
     * 通过ID删除一条记录,并且指定了集合名(表的意思) 
     *  
     * @param id  
     * @return 
     */  
    public boolean remove(Query query);
}
