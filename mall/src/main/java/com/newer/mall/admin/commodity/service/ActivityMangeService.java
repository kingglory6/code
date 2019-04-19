package com.newer.mall.admin.commodity.service;

import java.util.List;

import com.newer.mall.common.exception.DataException;
import com.newer.mall.common.pojo.Activity;

public interface ActivityMangeService {
	
	public void activity(Activity activity) throws DataException;
	
	public void dropActivity(int id);
	
	public List<Activity> findActivity();

}
