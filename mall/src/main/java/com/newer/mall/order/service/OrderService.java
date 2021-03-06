package com.newer.mall.order.service;


import java.text.ParseException;

import com.github.pagehelper.PageInfo;
import com.newer.mall.common.exception.NoStockException;
import com.newer.mall.common.pojo.Orders;
/**
  * 订单逻辑类
 * @author LiuLinJie
 *
 */
public interface OrderService {
	
	
	
    /**
         * 下单
     * @param orders(订单详细信息)
     * @param cartItems(购物车项，可以有多个)
     * @param uid(客户id)
     * @param remark(备注)
     * @return
     * @throws NoStockException 
     */
	public void addOrder(Orders orders,int uid) throws NoStockException;
	
	/**
	  * 查询订单
	 * @param uid(客户id)
	 * @param pagenum(页面页码)
	 * @param sendstatus(订单状态)
	 * @param paystatus(支付状态)
	 * @return
	 * @throws ParseException 
	 */
	public PageInfo<Orders> findOrders(int uid, int pagenum,int sendstatus ,int paystatus);
	
	/**
	  *  搜索订单
	 * @param uid(客户id)
	 * @param pagenum(页面页面)
	 * @param conditions(搜索条件)
	 * @return
	 */
	public PageInfo<Orders> searchOrders(int uid, int pagenum,String conditions);
	
	/**
	 *  删除订单
	 * @param oid(订单id)
	 * @return
	 */
	public void dltOrder(int oid);
	
   /**
        * 评论订单
    * @param uid(客户id)
    * @param cid(商品id)
    * @param content(评论内容)
    * @param score(评分)
    */
	public void addComment(int uid , int cid ,String content, int score	);
	
	/**
	 * 支付
	 * @param uid
	 * @param password
	 * @return
	 */
	public boolean pay(int uid , int oid,  String password);
	
	/**
	 * 查询已经删除的订单
	 * @param uid(客户id)
	 * @param pagenum(页面页面)
	 * @return
	 */
	public PageInfo<Orders> fdltOrders(int uid,int pagenum);
	/**
	 *  查询所有订单
	 * @param uid
	 * @param pagenum
	 * @return
	 */
	public PageInfo<Orders> allOrders(int uid, int pagenum);
	/**
	  * 修改订单状态
	 * @param oid
	 */
	public void upsay(int oid);
}
