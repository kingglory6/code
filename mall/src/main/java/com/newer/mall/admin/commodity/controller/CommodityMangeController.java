package com.newer.mall.admin.commodity.controller;

import java.util.Date;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.binding.BindingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.newer.mall.admin.commodity.service.impl.CommodityServiceImpl;
import com.newer.mall.common.exception.DataException;
import com.newer.mall.common.exception.StateException;
import com.newer.mall.common.pojo.Activity;
import com.newer.mall.common.pojo.Commodity;
import com.newer.mall.common.utils.EmailSenderService;

@RestController
@RequestMapping("api/v1/admin/commodity")
@CrossOrigin
public class CommodityMangeController {

	@Autowired
	CommodityServiceImpl service;

	@Autowired
	EmailSenderService mail;

	@GetMapping("/loadcommodity")
	public Map<String, Object> loadCommodity(@RequestParam("key") String reqkey, HttpSession session) {
		Map<String, Object> map = new HashMap<>();
		String key = (String) session.getAttribute("key");
		if (key == null) {
			map.put("code", "未登录");
		} else if (!reqkey.equals(key)) {
			map.put("code", "Illegal Request");
			return map;
		}
		map.put("data", service.findCommodity());
		return map;
	}

	@PostMapping("/add")
	public Map<String, Object> uploadCommodity(@RequestBody Commodity com, HttpSession session) {
		Map<String, Object> map = new HashMap<>();
		String key = (String) session.getAttribute("key");
		try {
			if (key == null) {
				map.put("code", "未登录");
				return map;
			}
			service.createCommodity(com);
			map.put("code", "ok");
		} catch (SQLException e) {
			map.put("code", "error");
		}
		return map;
	}

	@PostMapping("/state")
	public Map<String, Object> commodityState(int id, int option) {
		Map<String, Object> map = new HashMap<>();
		try {
			service.upDown(id, option);
			map.put("code", "ok");
		} catch (BindingException e) {
			map.put("code", "error");
		} catch (StateException e) {
			map.put("code", "state error");
		}
		return map;
	}

	@PutMapping("/stock")
	public Map<String, Object> stockMange(int id, int num) {
		Map<String, Object> map = new HashMap<>();
		try {
			service.stockMange(id, num);
			map.put("code", "ok");
		} catch (BindingException e) {
			map.put("code", "error");
		} catch (DataException e) {
			map.put("code", "data exception");
		}
		return map;
	}
	
	@PostMapping("/recommend")
	public Map<String, Object> recommendMange(int id, int type){
		Map<String, Object> map = new HashMap<>();
		try {
			service.recommend(id, type);
			map.put("code", "ok");
		} catch (DataException e) {
			map.put("code", "data exception");
		}
		return map;
	}
	
	@PostMapping("/activity")
	public Map<String, Object> activityMange(@RequestBody Activity activity){
		Map<String, Object> map = new HashMap<>();
		try {
			service.activity(activity);
			map.put("code", "ok");
		} catch (DataException e) {
			map.put("code", "type exception");
		}
		
		return map;
	}
	
	@GetMapping("/aa")
	public Date aaa() {
		return new Date();
	}

}
