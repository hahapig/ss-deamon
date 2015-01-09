package com.taihe.schedule.service;

import java.util.List;

import com.taihe.schedule.entity.Performer;
import com.taihe.schedule.entity.Site;

public interface InfoService {
	
	/** 
	 * 获取所有已通过审核的
	 * @return 
	 */
	public List<Performer> getAllPerformer();
	
	/** 
	 * 获取所有已通过审核的
	 * @return 
	 */
	public List<Site> getAllSite();
	
}
