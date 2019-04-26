package com.newer.mall.common.utils;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.newer.mall.common.mapper.AdminLoginMapper;
import com.newer.mall.common.pojo.Log;

@Service
public class LogService {

	@Autowired
	AdminLoginMapper mapper;
	
	public Page<Log> findLogs(Date startTime,Date endTime){
		return (Page<Log>) mapper.getLogs(startTime,endTime);
	}
	
	public void removeLog(int id) {
		mapper.deleteLog(id);
	}
}
