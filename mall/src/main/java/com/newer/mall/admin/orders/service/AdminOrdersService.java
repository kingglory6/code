package com.newer.mall.admin.orders.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.newer.mall.common.exception.DataException;
import com.newer.mall.common.mapper.AdminOrdersMapper;
import com.newer.mall.common.pojo.Orders;

@Service
public class AdminOrdersService {

	@Autowired
	AdminOrdersMapper mapper;
	
	//查询订单
	public Page<Orders> findOrders(String wl,String name,int paystatu,int payway,int send){
		return (Page<Orders>)mapper.getOrders(wl, name, paystatu, payway, send);
	}
	
	//修改订单
	public void saveOrders(Orders order) {
		mapper.updateOrders(order);
	}
	
	public void dropOrder(int id) {
		mapper.deleteOrder(id);
	}
	
	public void send(int id,String wuliu) throws DataException {
		if(wuliu == null || "".equals(wuliu)) {
			throw new DataException();
		}
		mapper.updateSendStatu(id, wuliu);
	}
	
	
	
}
