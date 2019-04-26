package com.newer.mall.admin.log;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.newer.mall.common.Annotation.UserLoginToken;
import com.newer.mall.common.pojo.Log;
import com.newer.mall.common.utils.LogService;

@RestController
@RequestMapping("api/v1/admin/log")
@UserLoginToken
public class LogController {
	
	@Autowired
	LogService service;
	
	@GetMapping("/loadlog/{page}")
	public Map<String,Object> loadLogs(@PathVariable int page,int size,Date startTime,Date endTime){
		Map<String,Object> map = new HashMap<>();
		PageHelper.startPage(page, size);
		map.put("data", new PageInfo<Log>(service.findLogs(startTime,endTime)));
		return map;
	}
	
	@Delete("/drop/{id}")
	public Map<String,Object> removeLog(@PathVariable int id){
		Map<String,Object> map = new HashMap<>();
		service.removeLog(id);
		map.put("code", "ok");
		return map;
	}

}
