package com.newer.mall.admin.commodity.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.newer.mall.admin.commodity.service.ActivityMangeService;
import com.newer.mall.common.Annotation.UserLoginToken;
import com.newer.mall.common.exception.DataException;
import com.newer.mall.common.pojo.Activity;

@RestController
@RequestMapping("api/v1/admin/commodity")
@UserLoginToken
public class ActivityMangeController {
	
	@Autowired
	ActivityMangeService service;
	
	@DeleteMapping("/removeactivity")
	public Map<String,Object> dropActivity(int id){
		Map<String, Object> map = new HashMap<>();
		service.dropActivity(id);
		map.put("code", "ok");
		return map;
	}
	
	@PostMapping("/activity")
	public Map<String, Object> activityMange(@RequestBody Activity activity){
		Map<String, Object> map = new HashMap<>();
		try {
			service.activity(activity);
			map.put("code", "ok");
		} catch (DataException e) {
			map.put("code", "data exception");
		}
		
		return map;
	}
	
	@GetMapping("/loadactivity")
	public Map<String,Object> loadActivity(int type){
		Map<String, Object> map = new HashMap<>();
		map.put("data", service.findActivity(type));
		return map;
	}

}
