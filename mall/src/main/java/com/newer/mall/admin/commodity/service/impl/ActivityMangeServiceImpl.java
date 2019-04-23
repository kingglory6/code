package com.newer.mall.admin.commodity.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.newer.mall.admin.commodity.service.ActivityMangeService;
import com.newer.mall.admin.commodity.thread.ActivityRunnable;
import com.newer.mall.common.exception.DataException;
import com.newer.mall.common.mapper.ActivityMangeMapper;
import com.newer.mall.common.pojo.Activity;

@Service
public class ActivityMangeServiceImpl implements ActivityMangeService {

	@Autowired
	ActivityMangeMapper mapper;

	@Override
	public void dropActivity(int id) {
		mapper.deleteActivity(id);
	}

	@Override
	public void activity(Activity activity) throws DataException {
		if (activity.getType() != 1 && activity.getType() != 2
				|| activity.getStartTime().getTime() < System.currentTimeMillis()
				|| activity.getEndTime().getTime() < activity.getStartTime().getTime()) {
			throw new DataException();
		}
		mapper.addActivity(activity);
		activity.setId(mapper.getActivityId());
		new Thread(new ActivityRunnable(activity, mapper)).start();

	}

	@Override
	public List<Activity> findActivity() {
		return mapper.getActivityList();
	}

}
