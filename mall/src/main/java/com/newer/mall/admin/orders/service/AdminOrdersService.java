package com.newer.mall.admin.orders.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.newer.mall.common.mapper.AdminOrdersMapper;
import com.newer.mall.common.pojo.Orders;

@Service
public class AdminOrdersService {

	@Autowired
	AdminOrdersMapper mapper;
	
	
	public Page<Orders> findOrders(String wl,String name,int paystatu,int payway,int send){
		return (Page<Orders>)mapper.getOrders(wl, name, paystatu, payway, send);
	}
}
