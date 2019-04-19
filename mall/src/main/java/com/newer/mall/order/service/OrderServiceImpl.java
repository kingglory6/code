package com.newer.mall.order.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.newer.mall.common.exception.NoStockException;
import com.newer.mall.common.mapper.CustomerOrderMapper;
import com.newer.mall.common.pojo.CartItem;
import com.newer.mall.common.pojo.CartItemParam;
import com.newer.mall.common.pojo.Comment;
import com.newer.mall.common.pojo.Orders;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
   
   
	@Autowired
	CustomerOrderMapper ordermapper;
	
	@Override
	public void addOrder(Orders orders, List<CartItem> cartItems,int uid,String remark)  {
		
		
		for(CartItem item : cartItems) {
			BigDecimal qtity = new BigDecimal(item.getQuantity());
			orders.setTotal(orders.getTotal().add(item.getCommodity().getPrice().multiply(qtity)));		
			ordermapper.addOrder(orders);
			ordermapper.addItme(item.getCommodity().getId(), ordermapper.findoid(uid), item.getSpec().getParam(),item.getQuantity(), remark);
		}
	

	}

	@Override
	public PageInfo<Orders> findOrders(int uid, int pagenum, int sendstatus) {
		// 进行分页
		PageHelper.startPage(pagenum, 10);
	    List<Orders> orders = ordermapper.findOrders(uid,sendstatus);	
		PageInfo<Orders> pageorders = new PageInfo<>(orders);
		
		return pageorders;
	}

	@Override
	public PageInfo<Orders> searchOrders(int uid, int pagenum, String conditions) {
		// 根据条件查询
		
		PageHelper.startPage(pagenum, 10);
		List<Orders> orders = ordermapper.serachOrders(uid, conditions);
		PageInfo<Orders> pageorders = new PageInfo<>(orders);
		
		return pageorders;
	}

	@Override
	public void dltOrder(int oid) {
		// 删除订单
		ordermapper.updtOrder(oid);
		
	}

	@Override
	public void addComment(Comment comment) {
		// 添加评论
		ordermapper.addComment(comment);
		
	}

	@Override
	public boolean nostock(List<CartItem> cartitem) throws NoStockException {
		// TODO Auto-generated method stub
		
	   for(CartItem item : cartitem) {
		   if(ordermapper.findstock(item.getCommodity().getId())>=item.getQuantity()) {
			   
			   ordermapper.upstock(item.getCommodity().getId(), item.getQuantity());
			   return true;
			   
		   }
		   else {
			   throw new NoStockException("库存不足");
		   }
	   }
		
		return false;
	}


}
