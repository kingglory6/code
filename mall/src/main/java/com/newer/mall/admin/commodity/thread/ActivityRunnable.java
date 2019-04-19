package com.newer.mall.admin.commodity.thread;

import java.util.Timer;
import java.util.TimerTask;

import com.newer.mall.common.mapper.ActivityMangeMapper;
import com.newer.mall.common.pojo.Activity;

public class ActivityRunnable implements Runnable {

	private Activity act;

	private ActivityMangeMapper mapper;

	private Timer timer = new Timer();;

	public ActivityRunnable(Activity act, ActivityMangeMapper mapper) {
		this.act = act;
		this.mapper = mapper;

	}

	@Override
	public void run() {
		if(act.getEndTime().getTime()>System.currentTimeMillis()) {
			activityStart();
		}
		activityEnd();
	}

	public void activityStart() {
		
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				if (mapper.getActivity(act.getId()) == null)
					return;
				
				if (act.getType() == 1) {
					mapper.activitySpikeStrat(act);
					System.out.println("活动开始");
				} else {
					mapper.activityDiscountStrat(act);
					System.out.println("活动开始");
				}

			}
		}, act.getStartTime());

	}

	public void activityEnd() {


		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				mapper.activityEnd(act.getCommodity().getId());
				mapper.activityStatu(act.getId());
				System.out.println("活动结束");
			}
		}, act.getEndTime());

	}

}
