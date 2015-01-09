package com.taihe.schedule.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.taihe.schedule.core.dynamic.DataSource;
import com.taihe.schedule.core.dynamic.DataSourceConst;
import com.taihe.schedule.core.persist.MogonPersistence;
import com.taihe.schedule.entity.Performer;
import com.taihe.schedule.entity.Site;
import com.taihe.schedule.service.InfoService;
import com.taihe.schedule.util.Constants;

@DataSource(DataSourceConst.MONGO_INFO_DB)
@Service
public class InfoServiceImp implements InfoService{
	
	@Autowired
	private MogonPersistence mogonPersistence;

	@Override
	public List<Performer> getAllPerformer(){
		Query query = new Query();
		query.addCriteria(Criteria.where("checked").is(Constants.USER_CHECK_SUCCESS));
		return mogonPersistence.find(query, Performer.class);
	}

	@Override
	public List<Site> getAllSite(){
		Query query = new Query();
		query.addCriteria(Criteria.where("checked").is(Constants.USER_CHECK_SUCCESS));
		return mogonPersistence.find(query, Site.class);
	}
}
