package com.newer.mall.order.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.newer.mall.common.pojo.CartItem;
import com.newer.mall.common.pojo.Comment;
import com.newer.mall.common.pojo.Orders;

@Service
public interface OrderService {
	
    /**
         * 下单
     * @param orders(订单详细信息)
     * @param cartItems(购物车项，可以有多个)
     * @return
     */
	public boolean addOrder(Orders orders , List<CartItem> cartItems);
	
	/**
	  * 查询订单
	 * @param uid(客户id)
	 * @param pagenum(页面页码)
	 * @return
	 */
	public PageInfo<Orders> findOrders(int uid, int pagenum);
	
	/**
	  *  搜索订单
	 * @param uid(客户id)
	 * @param pagenum(页面页面)
	 * @param conditions(搜索条件)
	 * @return
	 */
	public PageInfo<Orders> findOrders(int uid, int pagenum,String conditions);
	
	/**
	 *  删除订单
	 * @param oid(订单id)
	 * @return
	 */
	public boolean dltOrder(int oid);
	
	/**
	  *  评论订单
	 * @param comment(评论实体)
	 * @return
	 */
	public boolean addComment(Comment comment);
}
