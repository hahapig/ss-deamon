package com.taihe.schedule.job.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taihe.schedule.core.support.annotation.ExtendDisallowConcurrent;
import com.taihe.schedule.core.support.annotation.ScheduleAnnotation;
import com.taihe.schedule.entity.Performer;
import com.taihe.schedule.entity.Site;
import com.taihe.schedule.job.InfoJob;
import com.taihe.schedule.service.CalenderService;
import com.taihe.schedule.service.InfoService;

@ScheduleAnnotation
@Service("infoJob")
public class InfoJobImpl implements InfoJob{
	
	@Autowired
	private InfoService infoService;
	
	@Autowired
	private CalenderService calenderService;

	@Override
	@ExtendDisallowConcurrent
	@ScheduleAnnotation
	public void generateCalenderForSiteAndPerformer(){
		List<Performer> performers = infoService.getAllPerformer();
		List<Site> sites = infoService.getAllSite();
		for (Site site : sites) {
			calenderService.insertCalender(site.getUserInfo().getUserId(), site.getUserInfo().getUserType(), 180);
			if(site.isApproval()){//报批的场地，删除38天内的日程
				calenderService.deleteApprovaledCalendarInfo(site.getUserInfo().getUserId(), 38,site.getUserInfo().getUserType());
				
			}
		}
		for (Performer performer : performers) {
			calenderService.insertCalender(performer.getUserInfo().getUserId(), performer.getUserInfo().getUserType(), 180);
		}
	}
	
	@Override
	@ExtendDisallowConcurrent
	@ScheduleAnnotation
	public void deleteExpiredCalendarEvent(){
		calenderService.deleteExpiredCalendarEvent();
	}
}
