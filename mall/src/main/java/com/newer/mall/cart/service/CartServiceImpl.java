package com.newer.mall.cart.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.newer.mall.common.exception.NoStockException;
import com.newer.mall.common.mapper.CustomerCartMapper;
import com.newer.mall.common.mapper.CustomerMapper;
import com.newer.mall.common.mapper.CustomerOrderMapper;
import com.newer.mall.common.pojo.CartItem;
import com.newer.mall.common.pojo.Customer;

@Service
public class CartServiceImpl  implements CartService {
    
	@Autowired
	CustomerCartMapper cartMapper;
	
	@Autowired
	CustomerOrderMapper oMapper;
	

	
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
	 * @throws NoStockException 
	 */
	@Override
	public void changeQuantity(int uid , int sid ,int cid, int quantity) throws NoStockException {
		
		if(oMapper.findstock(cid)>=quantity) {
			cartMapper.changeQuantity(uid, cid, sid, quantity);
		}
		else {
			throw new NoStockException("库存不足");
		}
		
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
	 * @param cartItems
	 */
	@Override
	public void dltcarts(int uid,List<CartItem> cartItems) {
		
		for(CartItem item :cartItems) {
			cartMapper.dltCart(uid, item.getCommodity().getId(), item.getSpec().getId());
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

	/**
	  *结算
	 * @param uid(客户id)
	 * @return
	 */	 
	@Override
	public Customer settlement(int uid) {
		// TODO Auto-generated method stub
		return null;
	}


	
	
}
