package com.newer.mall.order.service;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.newer.mall.common.exception.NoStockException;
import com.newer.mall.common.mapper.CustomerOrderMapper;
import com.newer.mall.common.pojo.Item;
import com.newer.mall.common.pojo.Orders;

@Service
public class OrderServiceImpl implements OrderService {
   
   
	@Autowired
	CustomerOrderMapper ordermapper;
	
	BigDecimal add;
    
	//查询订单
	@Override
	public PageInfo<Orders> findOrders(int uid, int pagenum, int sendstatus, int paystatus) {
		// 进行分页
		PageHelper.startPage(pagenum, 10);
		
	    List<Orders> orders = ordermapper.findOrders(uid,sendstatus,paystatus);
	    for(Orders o : orders) {
//	       String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(o.getTime());
//	    	DateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//	    	
//	    	System.out.println(time);
	    	
//	    	try {
//				o.setTime(date.parse(time));
//				System.out.println(date.parseObject(time));
//			} catch (ParseException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
	    }
		PageInfo<Orders> pageorders = new PageInfo<>(orders);
		
		return pageorders;
	}
    
 
	
	
	//根据条件查询订单
	@Override
	public PageInfo<Orders> searchOrders(int uid, int pagenum, String conditions) {
		
		PageHelper.startPage(pagenum, 10);
		List<Orders> orders = ordermapper.serachOrders(uid, conditions);
		PageInfo<Orders> pageorders = new PageInfo<>(orders);
		
		return pageorders;
	}

	// 删除订单
	@Override
	public void dltOrder(int oid) {
		ordermapper.updtOrder(oid);
		
	}
   
	//添加评论
	@Override
	public void addComment(int uid, int cid, String content, int score) {
		// TODO Auto-generated method stub
		ordermapper.addComment(uid, cid, content, score);
	}
	
	
	
    //支付
	@Override
	public void paySuccess(int uid, int oid, int pay) {
		// TODO Auto-generated method stub
		
	}
   //下单
	@Override
	public void addOrder(Orders orders, int uid) throws NoStockException {
		add = new BigDecimal(0);
		 orders.setTotal(add);
		 for(Item item : orders.getItem()) {
		   if(ordermapper.findstock(item.getCommodity().getId())>=item.getQuantity()) {
     		    ordermapper.upstock(item.getCommodity().getId(), item.getQuantity());   
				BigDecimal qtity = new BigDecimal(item.getQuantity());
				orders.setTotal(orders.getTotal().add(qtity.multiply(item.getCommodity().getPrice())));	
		   }
		   else {	
	   throw new NoStockException("库存不足");
		   }
	   }
		 
		ordermapper.addOrder(orders);
		
       for(Item item : orders.getItem()) {
   	  
    	  ordermapper.addItme(ordermapper.findoid(item.getCommodity().getId()),item.getCommodity().getId(),item.getSpec().getParam(),item.getQuantity(),orders.getRemark());
       }		   
	}




	@Override
	public PageInfo<Orders> fdltOrders(int uid,int pagenum) {
		// TODO Auto-generated method stub
		
		PageHelper.startPage(pagenum, 10);
		List<Orders> orders = ordermapper.fdltOrders(uid);
		PageInfo<Orders> pageorders = new PageInfo<>(orders);
		
		return pageorders;
	}



}
