package com.newer.mall.admin.orders.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.newer.mall.admin.orders.service.AdminOrdersService;
import com.newer.mall.common.Annotation.UserLoginToken;
import com.newer.mall.common.exception.DataException;
import com.newer.mall.common.pojo.Orders;

@RestController
@RequestMapping("api/v1/admin/orders")
@UserLoginToken
public class AdminOrdersController {
	
	@Autowired
	AdminOrdersService service;
	
	@GetMapping("/loadorders/{page}")
	public Map<String,Object> loadOrders(@PathVariable int page, int size,String wl,String name,int paystatu,int payway,int send){
		Map<String,Object> map = new HashMap<>();
		PageHelper.startPage(page, size);
		if("".equals(wl)) {
			wl=null;
		}
		if("".equals(name)) {
			name=null;
		}
		map.put("data", new PageInfo<Orders>(service.findOrders(wl, name, paystatu, payway, send)));
		return map;
	}
	
	@PutMapping("/saveorder")
	public Map<String,Object> putOrders(@RequestBody Orders order){
		Map<String,Object> map = new HashMap<>();
		service.saveOrders(order);
		map.put("code", "ok");
		return map;
	}
	
	@Delete("/removeorder/{id}")
	public Map<String, Object> dropOrder(@PathVariable int id){
		Map<String,Object> map = new HashMap<>();
		service.dropOrder(id);
		map.put("code", "ok");
		return map;
	}
	
	@PutMapping("/sendhuo")
	public Map<String, Object> sendHuo(int id,String wuliu){
		Map<String,Object> map = new HashMap<>();
		try {
			service.send(id, wuliu);
			map.put("code", "ok");
		} catch (DataException e) {
			map.put("code", "data exception");
		}
		
		return map;
	}

}
