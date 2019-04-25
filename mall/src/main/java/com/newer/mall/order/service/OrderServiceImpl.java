package com.newer.mall.order.service;

import java.math.BigDecimal;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.ls.LSInput;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.newer.mall.common.exception.NoStockException;
import com.newer.mall.common.mapper.CustomerMapper;
import com.newer.mall.common.mapper.CustomerOrderMapper;
import com.newer.mall.common.pojo.Item;
import com.newer.mall.common.pojo.Orders;
import com.newer.mall.common.utils.Password;

@Service
public class OrderServiceImpl implements OrderService {
   
   
	@Autowired
	CustomerOrderMapper ordermapper;
	
	@Autowired
	CustomerMapper cMapprt;
	
	BigDecimal add;
    
	//查询订单
	@Override
	public PageInfo<Orders> findOrders(int uid, int pagenum, int sendstatus, int paystatus) {
		// 进行分页
		PageHelper.startPage(pagenum, 10);
		
	    List<Orders> orders = ordermapper.findOrders(uid,sendstatus,paystatus);
	    
	    for(Orders o : orders) {
	    	if(o.getPayStatus()==2 && o.getSendStatus()==0) {
	    		o.setZt("支付");
	    		System.out.println(o.getZt());
	    	}
	    	if(o.getPayStatus()==1 && o.getSendStatus()==0) {
	    		o.setZt("提醒发货");
	    	}
	    	if(o.getPayStatus()==1 && o.getSendStatus()==1) {
	    		o.setZt("确认到货");
	    	}
	       	if(o.getPayStatus()==1 && o.getSendStatus()==2) {
	    		o.setZt("进行评论");
	    	}
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
		 
		ordermapper.addOrder(uid,orders);
		
       for(Item item : orders.getItem()) {
   	  
    	  ordermapper.addItme(ordermapper.findoid(item.getCommodity().getId()),item.getCommodity().getId(),item.getSpec().getParam(),item.getQuantity(),orders.getRemark());
       }		   
	}



    // 查询删除订单
	@Override
	public PageInfo<Orders> fdltOrders(int uid,int pagenum) {
		// TODO Auto-generated method stub
		
		PageHelper.startPage(pagenum, 10);
		List<Orders> orders = ordermapper.fdltOrders(uid);
		   for(Orders o : orders) {
		    	if(o.getPayStatus()==2 && o.getSendStatus()==0) {
		    		o.setZt("支付");
		    		System.out.println(o.getZt());
		    	}
		    	if(o.getPayStatus()==1 && o.getSendStatus()==0) {
		    		o.setZt("提醒发货");
		    	}
		    	if(o.getPayStatus()==1 && o.getSendStatus()==1) {
		    		o.setZt("确认到货");
		    	}
		       	if(o.getPayStatus()==1 && o.getSendStatus()==2) {
		    		o.setZt("进行评论");
		    	}
		    }
		PageInfo<Orders> pageorders = new PageInfo<>(orders);
		
		return pageorders;
	}



    
	@Override
	public boolean pay(int uid, int oid, String password) {
		// TODO Auto-generated method stub
		String pword = Password.toSHA2(password);

		if(cMapprt.paypaword(uid).equals(pword)) {
			ordermapper.uppayway(uid, oid);
			return true;	
		}else {
			return false;
		}
	}




	@Override
	public PageInfo<Orders> allOrders(int uid, int pagenum) {
		// TODO Auto-generated method stub
		
		PageHelper.startPage(pagenum, 10);
		List<Orders> allod = ordermapper.allOd(uid);
	    for(Orders o : allod) {
	    	if(o.getPayStatus()==2 && o.getSendStatus()==0) {
	    		o.setZt("支付");
	    		System.out.println(o.getZt());
	    	}
	    	if(o.getPayStatus()==1 && o.getSendStatus()==0) {
	    		o.setZt("提醒发货");
	    	}
	    	if(o.getPayStatus()==1 && o.getSendStatus()==1) {
	    		o.setZt("确认到货");
	    	}
	       	if(o.getPayStatus()==1 && o.getSendStatus()==2) {
	    		o.setZt("进行评论");
	    	}
	    }
		PageInfo<Orders> page = new PageInfo<>(allod);
		
		return page;
	}




	@Override
	public void upsay(int oid) {
		// TODO Auto-generated method stub
		ordermapper.upsendstatus(oid);
		
	}



}
