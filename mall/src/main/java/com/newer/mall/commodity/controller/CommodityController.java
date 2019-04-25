package com.newer.mall.commodity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.newer.mall.commodity.service.CommodityService;
import com.newer.mall.common.pojo.Activity;
import com.newer.mall.common.pojo.Brand;
import com.newer.mall.common.pojo.CartItem;
import com.newer.mall.common.pojo.Category;
import com.newer.mall.common.pojo.Comment;
import com.newer.mall.common.pojo.Commodity;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class CommodityController {
	
	@Autowired
	CommodityService commservice;
	/**
	 * 查询分类显示商品
	 * @param pageNum
	 * @param categoryid
	 * @return
	 */
	@GetMapping("/commodity/{categoryid}/{pageNum}")
	public PageInfo<Commodity> displayCommodity(@PathVariable int categoryid,@PathVariable int pageNum){
		return commservice.displayCommodityCategory(pageNum, categoryid);
	}
	
	
	/**
	 * 查看推荐商品
	 * @param pageNum
	 * @return
	 */
	@GetMapping("/commodity/recommed/{pageNum}")
	public PageInfo<Commodity>displayRecommed(@PathVariable int pageNum){
		
		return commservice.displayCommodityRecommed(pageNum);
	}
	
	
	/**
	 * 查看限量折扣商品
	 * @param pageNum
	 * @return
	 */
	@GetMapping("/commodity/activity/{pageNum}")
	public PageInfo<Activity> queryActivity(@PathVariable int pageNum){
		
		return commservice.queryCommodityActivity(pageNum);
	}
	
//	/**
//	 * 查看限时折扣商品
//	 * @param pageNum
//	 * @return
//	 */
//	@GetMapping("/discount/{pageNum}")
//	public PageInfo<Activity>	queryCommodityDiscount(@PathVariable int pageNum){
//		return commservice.queryCommodityDiscount(pageNum);
//		
//	}
	
	/**
	 * 查看该品牌商品信息
	 * @param pageNum
	 * @param brandid
	 * @return
	 */
	@GetMapping("/commodity/brand/{brandid}/{pageNum}")
	public PageInfo<Commodity>queryCommodityByBrand(@PathVariable int pageNum,@PathVariable int brandid){
		return commservice.queryCommodityByBrand(pageNum, brandid);
	}
	
	/**
	 * 根据关键字查商品
	 * @param pageNum
	 * @param commodityname
	 * @return
	 */
	@GetMapping("/commodity/showcom/{commname}/{pageNum}")
	public PageInfo<Commodity>	queryCommodityByName(@PathVariable int pageNum,@PathVariable String commname){
		
		return commservice.queryCommodityByName(pageNum, commname);
		
	}

	/**
	 * 查看商品详细信息
	 * @param commodityid
	 * @return
	 */
	@GetMapping("/commodity/{commodityid}")
	public Commodity queryCommodity(@PathVariable int commodityid) {
		return commservice.queryCommodity(commodityid);
	}	
	

	
	/**
	 * 添加至购物车
	 * @param uid
	 * @param commodityid
	 * @param quantity
	 * @param param
	 * @return
	 */
	@PostMapping("/cart/addcart")
	public  List<CartItem>  addCart(@RequestParam int uid,
									@RequestParam int commodityid,
									@RequestParam int quantity,
									@RequestParam String param)
	{
		return commservice.addCart(uid, commodityid, quantity, param);
		
	}
	
	/**
	 * 查看商品评论
	 * @param pageNum
	 * @param commodityid
	 * @return
	 */
	@GetMapping("/commodity/common/{commodityid}/{pageNum}")
	public PageInfo<Comment> queryCommoditycomment(@PathVariable int pageNum,@PathVariable int commodityid){
		return commservice.queryCommoditycomment(pageNum, commodityid);
	}
	
	/**
	 * 显示所有商品
	 * @param pageNum
	 * @return
	 */
	@GetMapping("/commodity/allcomm/{pageNum}")
	public PageInfo<Commodity> queryAllCommodity(@PathVariable int pageNum){
		
		return commservice.showAllCommodity(pageNum);
	}
	/**
	 * 显示所有类别
	 * @return
	 */
	@GetMapping("/commodity/category")
	public List<Category> queryCategory(){
		return commservice.showCategory();
	}
	
	
	/**
	 * 显示所有品牌
	 * @return
	 */
	@GetMapping("/commodity/brand")
	public List<Brand> queryBrand(){
		return commservice.showBrand();
	}

}
