package com.newer.mall.admin.account.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.newer.mall.admin.account.service.CommodityService;
import com.newer.mall.common.pojo.Commodity;

@RestController
@RequestMapping("api/v1/admin")
@CrossOrigin
public class CommodityMangeController {

	@Autowired
	CommodityService service;

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
		} catch (SQLException e) {
			map.put("code", "error");
			return map;
		}
		map.put("code", "ok");
		return map;
	}

}
