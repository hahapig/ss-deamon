package com.taihe.schedule.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.taihe.schedule.core.persist.MogonPersistence;
import com.taihe.schedule.entity.Fund;
import com.taihe.schedule.service.FundService;

@Service
public class FundServiceImpl implements FundService {
	
	@Autowired
	private MogonPersistence mogonPersistence;

	@Override
	public Fund getFundInfoById(String Id){
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(Id));
		return mogonPersistence.findById(Id, Fund.class);
	}
	
}
