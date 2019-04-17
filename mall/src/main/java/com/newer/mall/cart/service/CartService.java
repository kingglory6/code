package com.newer.mall.cart.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.newer.mall.common.pojo.CartItem;

@Service
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
	 * @param quantity(商品数量)
	 */
	public boolean changeQuantity(int uid , int sid , int quantity);
	
	/**
	  *  删除购物车项
	 * @param uid(客户id)
	 * @param sid(商品规格id)
	 */
	public boolean dltcart(int uid, int sid);
	
	/**
	  * 根据条件查询购物车项
	 * @param uid(客户id)
	 * @param pagenum(页面页码)
	 * @param conditions (搜索条件)
	 * @return
	 */	
	public PageInfo<CartItem> findCart(int uid , String conditions ,int pagenum);
	
	
}
