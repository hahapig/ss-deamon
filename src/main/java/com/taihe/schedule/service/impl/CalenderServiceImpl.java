package com.taihe.schedule.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.taihe.schedule.core.dynamic.DataSource;
import com.taihe.schedule.core.dynamic.DataSourceConst;
import com.taihe.schedule.core.persist.MogonPersistence;
import com.taihe.schedule.entity.CalendarEvent;
import com.taihe.schedule.service.CalenderService;
import com.taihe.schedule.util.Constants;

@DataSource(DataSourceConst.MONGO_CALENDER_DB)
@Service
public class CalenderServiceImpl implements CalenderService{
	
	@Autowired
	private MogonPersistence mogonPersistence;
	
	public void insertCalender(String uId,int userType,int days){
		DateTime today = new DateTime().withTimeAtStartOfDay();//今日0点
		List<CalendarEvent> calendarEvents = new ArrayList<CalendarEvent>(1);

    	CalendarEvent calendarEvent = new CalendarEvent();
        calendarEvent.setUserId(uId);
        calendarEvent.setUserType(userType);
        calendarEvent.setType(Constants.CALENDER_RESERVATION);
        DateTime theDay = today.plusDays(days);
        calendarEvent.setDay(theDay.toDate());
        calendarEvent.setStart(theDay.plusHours(12).toDate());
        calendarEvent.setEnd(theDay.plusHours(23).plusMinutes(59).toDate());
        calendarEvents.add(calendarEvent);
        //--------------------------------final step------------------------------------------------
        mogonPersistence.save(calendarEvent);
	}
	
	public void deleteExpiredCalendarEvent(){
		DateTime today = new DateTime().withTimeAtStartOfDay();//今日0点
		DateTime theDay = today.plusDays(8);
		Query query = new Query();
		Criteria criteria =  new Criteria();
		criteria.and("end").lt(theDay.plusHours(23).toDate());
		query.addCriteria(criteria);
		mogonPersistence.remove(query, CalendarEvent.class);
	}

	@Override
	public void deleteApprovaledCalendarInfo(String uid, int period,int userType){
		DateTime today = new DateTime().withTimeAtStartOfDay();//今日0点
		DateTime theDay = today.plusDays(period);
		Query query = new Query();
		Criteria criteria =  new Criteria();
		criteria.and("end").lt(theDay.plusHours(23).toDate());
		criteria.and("userId").is(uid);
		criteria.and("userType").is(userType);
		query.addCriteria(criteria);
		mogonPersistence.remove(query, CalendarEvent.class);
	}
}
