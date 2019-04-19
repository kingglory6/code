package com.newer.mall.order.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
@RequestMapping("/api/v1/order")
@CrossOrigin
public class OrderController {
     
	@Autowired
	OrderService oservice;
	
	//判断下单时库存是否充足
	@PostMapping("/stock")
	public boolean nostock(@RequestBody List<CartItem> cartItems) throws NoStockException {
		
		for(CartItem item : cartItems) {
			System.out.println(item.getCommodity().getTitle());
		}
		
		return oservice.nostock(cartItems);
	}
	
	
	//添加订单
	@PostMapping("/add")
	public void addOrder(@RequestParam int uid,
			             @RequestBody Orders orders ,
			             @RequestBody List<CartItem> cartitems,
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
    public void dltOrder(@RequestParam int oid) {
    	
    	oservice.dltOrder(oid);
    }
    
    //添加评论
    @PostMapping("/addcment")
    public void addComment(@RequestBody Comment comment) {
    	
    	oservice.addComment(comment);
    }
    
    //搜索订单
    @PostMapping("/search")
    public PageInfo<Orders> search(int uid ,int pagenum, String conditions){
    	
		return oservice.searchOrders(uid, pagenum, conditions);
    	
    }

}
