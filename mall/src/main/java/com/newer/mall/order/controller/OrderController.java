package com.newer.mall.order.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.newer.mall.common.exception.NoStockException;
import com.newer.mall.common.pojo.CartItem;
import com.newer.mall.common.pojo.Comment;
import com.newer.mall.common.pojo.Orders;
import com.newer.mall.order.service.OrderService;

@RestController
@RequestMapping("api/v1/order")
@CrossOrigin
public class OrderController {
     
	@Autowired
	OrderService oservice;
	//添加订单
	@PostMapping("/add")
	public void addOrder(@RequestParam int uid,
			             @RequestParam Orders orders ,
			             @RequestParam List<CartItem> cartitems,
			             @RequestParam String remark) throws NoStockException {

		oservice.addOrder(orders, cartitems, uid,remark);
	}
	//查询订单
    @PostMapping("/find")
	public PageInfo<Orders> findOrders(@RequestParam int uid,
			                           @RequestParam int pagenum,
			                           @RequestParam int sendstatus){
    	
		return oservice.findOrders(uid, pagenum, sendstatus);
	}
    //删除订单
    @PostMapping("/dlt")
    public void dltOrder(int oid) {
    	
    	oservice.dltOrder(oid);
    }
    
    //添加评论
    @PostMapping("/addcment")
    public void addComment(Comment comment) {
    	
    	oservice.addComment(comment);
    	
    }

}
