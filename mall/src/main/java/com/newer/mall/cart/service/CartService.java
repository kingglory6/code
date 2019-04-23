package com.newer.mall.cart.service;

import java.util.List;
import java.util.Map;



import com.github.pagehelper.PageInfo;
import com.newer.mall.common.exception.NoStockException;
import com.newer.mall.common.pojo.CartItem;
import com.newer.mall.common.pojo.Customer;

public interface CartService {
	/**
	  * 查询购物车列表
	 * @param uid(客户id)
	 * @param pagenum(页面页码)
	 * @return
	 */
	public PageInfo<CartItem> checkCart(int uid,int pagenum);
	
	/**
	  *  修改购物车中商品的数量
	 *  
	 * @param uid(客户id)
	 * @param sid(商品规格id)
	 * @param cid(商品id)
	 * @param quantity(商品数量)
	 * @throws NoStockException 
	 */
	public void changeQuantity(int uid , int sid ,int cid, int quantity) throws NoStockException ;
	
	/**
	  *  删除购物车项
	 * @param uid(客户id)
	 * @param sid(商品规格id)
	 * @param cid(商品id)
	 */
	public void dltcart(int uid, int sid,int cid);
	
	/**
	  * 购物车多项删除
	 * @param uid(客户id)
	 * @param dltmap(包含规格id 商品id)
	 */
	public void dltcarts(int uid,List<CartItem> cartItems);
	
	/**
	  * 根据条件查询购物车项
	 * @param uid(客户id)
	 * @param pagenum(页面页码)
	 * @param conditions (搜索条件)
	 * @return
	 */	
	public PageInfo<CartItem> findCart(int uid , String conditions ,int pagenum);
	
	
	/**
	  *  结算
	 * @param uid(客户id)
	 * @return
	 */
	public Customer settlement(int uid);
}
