package com.newer.mall.order.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.newer.mall.common.exception.NoStockException;
import com.newer.mall.common.mapper.CustomerOrderMapper;
import com.newer.mall.common.pojo.CartItem;
import com.newer.mall.common.pojo.CartItemParam;
import com.newer.mall.common.pojo.Comment;
import com.newer.mall.common.pojo.Orders;

public class OrderServiceImpl implements OrderService {
 
	@Autowired
	CustomerOrderMapper ordermapper;
	
	@Override
	public void addOrder(Orders orders, List<CartItem> cartItems,int uid) throws NoStockException {
		
		for(CartItem cartitem : cartItems) {
		   for(Map.Entry<Integer, CartItemParam> entry : cartitem.getParam().entrySet()) {
			   //进行是否库存充足的判断
			   if(ordermapper.findstock(entry.getKey())>= entry.getValue().getQuantity()) {
				   ordermapper.addOrder(orders,uid);
				   ordermapper.addItme(cartitem, ordermapper.findoid(uid));
			   }else {
				  throw new NoStockException("库存不足");
			   }
			   
		   }
		}
	}

	@Override
	public PageInfo<Orders> findOrders(int uid, int pagenum) {
		// 进行分页
		PageHelper.startPage(pagenum, 10);
	    List<Orders> orders = ordermapper.findOrders(uid);	
		PageInfo<Orders> pageorders = new PageInfo<>(orders);
		
		return pageorders;
	}

	@Override
	public PageInfo<Orders> findOrders(int uid, int pagenum, String conditions) {
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

}
