package com.newer.mall.common.utils;

import java.io.File;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {
	private File file = new File("G:/image/temp");
	
	@Scheduled(fixedRate = 1000*60*60*24)
	 public void deleteTemp() {
		File[] files = file.listFiles();
		for(File f:files) {
			f.delete();
		}
	 }
	
}
