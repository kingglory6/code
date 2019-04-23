
package com.newer.mall.cart.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.newer.mall.cart.service.CartService;
import com.newer.mall.common.exception.NoStockException;
import com.newer.mall.common.pojo.CartItem;

 
@RestController
@RequestMapping("/api/v1/cart")
@CrossOrigin
public class CartController {
	
	@Autowired
    CartService cartservice;
	
	
	/**
	  *  查询购物车项
	 * @param uid
	 * @param pagenum
	 * @return
	 */
	@PostMapping("/check")
	public PageInfo<CartItem> checkCart(@RequestParam int uid , @RequestParam int pagenum){
		
		
		return cartservice.checkCart(uid, pagenum);
		
	}
	
	
	/**
	  * 修改商品中的数量
	 * @param uid
	 * @param sid
	 * @param quantity
	 * @throws NoStockException 
	 */
	
	@PostMapping("/change")
	public void changeQuantity(@RequestParam int uid ,
			                   @RequestParam int sid ,
			                   @RequestParam int cid,
			                   @RequestParam int quantity) throws NoStockException {
		
		cartservice.changeQuantity(uid, sid, cid, quantity);
		
	}
	/**
	  * 删除购物车
	 * @param uid
	 * @param sid
	 */
	
	@PostMapping("/dlt")	
	public void dltcart(@RequestParam int uid ,@RequestParam int sid , @RequestParam int cid) {
	   cartservice.dltcart(uid, sid, cid);
	}
	
	/**
	  * 多项删除
	 * @param uid
	 * @param dltmap
	 */
	@PostMapping("/dlts")
	public void dltcarts(@RequestParam int uid, List<CartItem> cartItems) {
		
		
     	cartservice.dltcarts(uid, cartItems);
	}
	
	/**
	  * 搜索购物车
	 * @param uid
	 * @param conditions
	 * @param pagenum
	 * @return
	 */
	@PostMapping("/serach")
	public PageInfo<CartItem> findCart(@RequestParam int uid ,
			                           @RequestParam String conditions ,
			                           @RequestParam int pagenum){
		
		
		return cartservice.findCart(uid, conditions, pagenum);
		
	}
	
	
}
