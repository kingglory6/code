package com.newer.mall.admin.commodity.thread;

import java.util.Timer;
import java.util.TimerTask;

import com.newer.mall.common.mapper.CommodityMangeMapper;
import com.newer.mall.common.pojo.Activity;

public class ActivityRunnable implements Runnable {

	private Activity act;

	private CommodityMangeMapper mapper;

	private Timer timer = new Timer();;

	public ActivityRunnable(Activity act, CommodityMangeMapper mapper) {
		this.act = act;
		this.mapper = mapper;

	}

	@Override
	public void run() {
		activityStart();
		activityEnd();
	}

	public void activityStart() {
			
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				if (act.getType() == 1) {
					mapper.activitySpikeStrat(act);
				} else {
					mapper.activityDiscountStrat(act);
				}

			}
		}, act.getStartTime());
		
		System.out.println(act);
		
		//服务器启动时,将已过活动开始时间,尚未结束的活动开始
		if (act.getStartTime().getTime() > System.currentTimeMillis()
				&& act.getStartTime().getTime() < act.getEndTime().getTime()) {
			if (act.getType() == 1) {
				mapper.activitySpikeStrat(act);
			} else {
				mapper.activityDiscountStrat(act);
			}
		}
	}

	public void activityEnd() {
		
		if(System.currentTimeMillis()>act.getEndTime().getTime()) {
			mapper.activityEnd(act.getCommodity().getId());
			mapper.activityStatu(act.getId());
		}

		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				mapper.activityEnd(act.getCommodity().getId());
				mapper.activityStatu(act.getId());
			}
		}, act.getEndTime());

	}

}
