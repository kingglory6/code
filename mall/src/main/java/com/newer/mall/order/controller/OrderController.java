package com.newer.mall.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	public void addOrder(@RequestParam int uid,
			             @RequestBody Orders orders) throws NoStockException {   
		
		oservice.addOrder(orders,uid);
	}
	//查询订单
    @GetMapping("/find/{pagenum}/{sendstatus}/{paystatus}")
	public PageInfo<Orders> findOrders(@RequestParam int uid,
			                           @PathVariable int pagenum,
			                           @PathVariable int sendstatus,
			                           @PathVariable int paystatus) {
    	
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
    @GetMapping("/search/{conditions}/{pagenum}")
    public PageInfo<Orders> search(@RequestParam int uid,
    		                       @PathVariable int pagenum,
    		                       @PathVariable String conditions
    		){
    	
		return oservice.searchOrders(uid, pagenum, conditions);
    	
    }	
    //查看已经删除的订单
    @GetMapping("/fdlt/{pagenum}")
    public PageInfo<Orders> fdlt(@RequestParam int uid ,@PathVariable  int pagenum){
    	
    	return oservice.fdltOrders(uid, pagenum);
    	
    }
    //支付
    @PostMapping("/pay")
	public boolean pay(@RequestParam int uid,@RequestParam int oid, @RequestParam String password) {
		return oservice.pay(uid, oid, password);		
	}
    
    @GetMapping("/all/{pagenum}")
    public PageInfo<Orders> allOrders(@RequestParam("uid")int uid ,@PathVariable int pagenum){
    	
    	return oservice.allOrders(uid, pagenum);
    	
    }
    /**
         * 修改订单状态
     * @param oid
     */
    @PostMapping("/up")
    public void upsay (@RequestParam int oid) {
         oservice.upsay(oid);
    }
    
}
