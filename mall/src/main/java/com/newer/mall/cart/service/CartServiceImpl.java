package com.newer.mall.cart.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.newer.mall.common.mapper.CustomerCartMapper;
import com.newer.mall.common.pojo.CartItem;

@Service
public class CartServiceImpl  implements CartService {
    
	@Autowired
	CustomerCartMapper cartMapper;
	
	/**
	  * 查询购物车列表
	 * @param uid(客户id)
	 * @param pagenum(页面页码)
	 * @return
	 */
	@Override
	public PageInfo<CartItem> checkCart(int uid,int pagenum){
		
		//进行分页查询
		PageHelper.startPage(pagenum, 10);
		List<CartItem> cartitem = cartMapper.checkCart(uid);
		PageInfo<CartItem> pageitem = new PageInfo<>(cartitem);
		return pageitem;
	}
	
	
	/**
	  *  修改购物车中商品的数量
	  *  
	 * @param uid(客户id)
	 * @param sid(商品规格id)
	 * @param cid(商品id)
	 * @param quantity(商品数量)
	 */
	@Override
	public void changeQuantity(int uid , int sid ,int cid, int quantity) {
		cartMapper.changeQuantity(uid, cid, sid, quantity);
	}
	
	/**
	  *  删除购物车项
	 * @param uid(客户id)
	 * @param sid(商品规格id)
	 * @param cid(商品id)
	 */
	@Override
	public void dltcart(int uid, int sid,int cid) {
		
		cartMapper.dltCart(uid, cid, sid);
	}	
	
	/**
	  * 购物车多项删除
	 * @param uid
	 * @param dltmap
	 */
	@Override
	public void dltcarts(int uid,Map<Integer, Integer>dltmap) {
		//循环遍历出map里面商品id 和规格id
		for(Map.Entry<Integer, Integer> entry : dltmap.entrySet()) {
		      cartMapper.dltCart(uid,entry.getKey() , entry.getValue());
		}
	}
	
	
	/**
	  * 根据条件查询购物车项
	 * @param uid(客户id)
	 * @param pagenum(页面页码)
	 * @param conditions (搜索条件)
	 * @return
	 */	
	@Override
	public PageInfo<CartItem> findCart(int uid , String conditions ,int pagenum){
		
		PageHelper.startPage(pagenum, 10);
		List<CartItem> cartitem = cartMapper.findcart(uid, conditions);
		PageInfo<CartItem> pageitem = new PageInfo<>(cartitem);
		return pageitem;
	}
	
	
}
