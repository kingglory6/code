package com.newer.mall.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.newer.mall.common.exception.NoStockException;
import com.newer.mall.common.pojo.Orders;
import com.newer.mall.order.service.OrderService;

@RestController
@RequestMapping("/api/v1/order")
@CrossOrigin
public class OrderController {
     
	@Autowired
	OrderService oservice;
	
	//添加订单
	@PostMapping("/add")
	public void addOrder(
			             @RequestBody Orders orders) throws NoStockException {   	
	    int uid =1;
		oservice.addOrder(orders,uid);
	}
	//查询订单
    @PostMapping("/find")
	public PageInfo<Orders> findOrders(@RequestParam int uid,
			                           @RequestParam int pagenum,
			                           @RequestParam int sendstatus,
			                           @RequestParam int paystatus) {
    	
		return oservice.findOrders(uid, pagenum, sendstatus, paystatus);
	}
    //删除订单
    @PostMapping("/dlt")
    public void dltOrder(@RequestParam int oid) {
    	
    	oservice.dltOrder(oid);
    }
    
    //添加评论
    @PostMapping("/addcment")
    public void addComment(@RequestParam int uid ,@RequestParam int cid ,@RequestParam String content , @RequestParam int score) {
    	
    	oservice.addComment(uid , cid , content , score);
    }
    
    //搜索订单
    @PostMapping("/search")
    public PageInfo<Orders> search(@RequestParam int uid ,@RequestParam int pagenum,@RequestParam String conditions){
    	
		return oservice.searchOrders(uid, pagenum, conditions);
    	
    }
    //查看已经删除的订单
    @PostMapping("/fdlt")
    public PageInfo<Orders> fdlt(@RequestParam int uid ,@RequestParam  int pagenum){
    	
    	return oservice.fdltOrders(uid, pagenum);
    }
    

}
