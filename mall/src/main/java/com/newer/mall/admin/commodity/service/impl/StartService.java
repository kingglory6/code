package com.newer.mall.admin.commodity.service.impl;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.newer.mall.admin.commodity.thread.ActivityRunnable;
import com.newer.mall.common.mapper.CommodityMangeMapper;
import com.newer.mall.common.pojo.Activity;

@Component
public class StartService implements ApplicationRunner{
	
	@Autowired
	CommodityMangeMapper mapper;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		List<Activity> list = mapper.getActivity();
		ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
		for(Activity act:list) {
			cachedThreadPool.execute(new ActivityRunnable(act, mapper));
		}
		
	}

}
