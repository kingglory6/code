package com.newer.mall.admin.orders.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.newer.mall.admin.orders.service.AdminOrdersService;

@RestController
@RequestMapping("v1/admin/orders")
public class AdminOrdersController {
	
	@Autowired
	AdminOrdersService service;
	
	@GetMapping("/loadOrders")
	public Map<String,Object> loadOrders(String wl,String name,int paystatu,int payway,int send){
		Map<String,Object> map = new HashMap<>();
		map.put("data", new PageInfo<>(service.findOrders(wl, name, paystatu, payway, send)));
		return map;
	}

}
