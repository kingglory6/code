package com.newer.mall.cart.controller;


import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.newer.mall.common.pojo.CartItem;
 
@RestController
@RequestMapping("api/v1/cart")
public class CartController {
    
	
	@PostMapping("/{uid}/{pagenum}")
	public PageInfo<CartItem> checkCart(@PathVariable int uid ,@PathVariable int pagenum){
		
		return null;
		
	}
	
}
