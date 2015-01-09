package com.taihe.schedule.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.taihe.schedule.core.dynamic.DataSource;
import com.taihe.schedule.core.dynamic.DataSourceConst;
import com.taihe.schedule.core.persist.ActivityPersistenceImpl;
import com.taihe.schedule.entity.Activity;
import com.taihe.schedule.entity.ActivityPrice;
import com.taihe.schedule.service.ActivityService;
import com.taihe.schedule.util.ActivityType;
import com.taihe.schedule.util.Constants;

@DataSource(DataSourceConst.MONGO_ACTIVITY_DB)
@Service
public class ActivityServiceImpl implements ActivityService {

    final static Logger logger = LoggerFactory.getLogger(ActivityServiceImpl.class);
    @Autowired
    private ActivityPersistenceImpl persistenceImpl;

    @Override
    public List<Activity> getShowingActivities(long delayTime) {//获取演出已结束的活动
        Query query = new Query();
        Date date = new Date();
        long time = date.getTime() + delayTime;
        Criteria criteria = new Criteria();
        criteria.and("level").is(Constants.SHOW);
        criteria.and("showEndDate").lte(new Date(time));
        query.addCriteria(criteria);
        List<Activity> activities = persistenceImpl.find(query);
        return activities;
    }

    public int updateActivitiesCompleted(List<Activity> activities) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int updateActivitiesCompleted(Activity activity) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(activity.getId()));
        Update update = new Update();
        update.set("level", Constants.SHOW_OVER);
        persistenceImpl.update(query, update);
        return 1;
    }

    @Override
    public int updateActivityStatusById(List<Activity> activities) {
        for (Activity activity : activities) {
            Query query = new Query();
            query.addCriteria(Criteria.where("_id").is(activity.getId()));
            Update update = new Update();
            update.set("level", activity.getLevel());
            persistenceImpl.update(query, update);
        }
        return 1;
    }

    @Override
    public List<Activity> getAllCollectingAct() {
        Query query = new Query();
        Criteria criteria = new Criteria();
        criteria.and("level").is(Constants.COLLECT_MONEY);
        query.addCriteria(criteria);
        List<Activity> activities = persistenceImpl.find(query);
        return activities;
    }

    @Override
    public List<Activity> getCompletedAct() {//获取状态类 level=6的活动，生成报表
        Query query = new Query();
        Criteria criteria = new Criteria();
        criteria.and("level").is(Constants.SHOW_OVER);
        query.addCriteria(criteria);
        List<Activity> activities = persistenceImpl.find(query);
        return activities;
    }

    @Override
    public List<Activity> getPreparationExpired() {//获取type=1和2 已过筹备期的活动
        Query query = new Query();
        Criteria criteria = new Criteria();
        criteria.and("type").in(ActivityType.TOUR,ActivityType.NORMAL);
        criteria.and("level").is(Constants.COLLECT_MONEY);
        criteria.and("preparation").lte(new Date());
        query.addCriteria(criteria);
        List<Activity> activities = persistenceImpl.find(query);
        return activities;
    }
    
    @Override
	public List<Activity> getActivityExpiredByRelease() {//获取type=3 已过筹备期的活动
		Query query = new Query();
        Criteria criteria = new Criteria();
        criteria.and("type").is(ActivityType.RELEASE);
        criteria.and("level").is(Constants.COLLECT_MONEY);
        criteria.and("preparation").lte(new Date());
        query.addCriteria(criteria);
        List<Activity> activities = persistenceImpl.find(query);
        return activities;
	}

    @Override
    public int updateActStatus(String actId, int status) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(actId));
        Update update = new Update();
        update.set("level", status);
        persistenceImpl.update(query, update);
        return 1;
    }

    @Override
    public int updateToShowing() {//更新type为1、2的活动为演出中
        Query query = new Query();
        query.addCriteria(Criteria.where("showStartDate").lte(new Date()));
        //query.addCriteria(Criteria.where("type").is());
        query.addCriteria(Criteria.where("level").in(Constants.COLLECT_MONEY_FINISHED, Constants.SELL_OVER,Constants.SELL_COST));
        Update update = new Update();
        update.set("level", Constants.SHOW);
        persistenceImpl.update(query, update);
        return 1;
    }
    
    @Override
	public int updateTrailerToShowing() {//更新type为4的活动为演出中
    	 Query query = new Query();
    	 Criteria criteria = new Criteria();
    	 criteria.and("showStartDate").lte(new Date());
    	 criteria.and("type").is(ActivityType.TRAILER);
    	 criteria.and("level").is(Constants.COLLECT_MONEY);
    	 query.addCriteria(criteria);
         Update update = new Update();
         update.set("level", Constants.SHOW);
         persistenceImpl.update(query, update);
		return 1;
	}
 
	@Override
	public int updateActStatusAndCost(String actId, int status, ActivityPrice price) {
		Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(actId));
        Update update = new Update();
        update.set("level", status);
        update.set("prices", price);
        persistenceImpl.update(query, update);
        return 1;
	}

	@Override
	public List<Activity> getActivityExpiredTest() {
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is("54583d0745ce8ef80a19ed5c"));
        List<Activity> activities = persistenceImpl.find(query);
        return activities;
	}
}
