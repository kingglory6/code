package com.newer.mall.order.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.newer.mall.common.exception.NoStockException;
import com.newer.mall.common.pojo.CartItem;
import com.newer.mall.common.pojo.Orders;
import com.newer.mall.order.service.OrderService;

@RestController
@RequestMapping("api/v1/order")
@CrossOrigin
public class OrderController {
     
	@Autowired
	OrderService oservice;
	
	@PostMapping("/add")
	public void addOrder(@RequestParam int uid,
			             @RequestParam Orders orders ,
			             @RequestParam List<CartItem> cartitems) throws NoStockException {

		oservice.addOrder(orders, cartitems, uid);
	}

}
