package com.newer.mall.commodity.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.newer.mall.common.pojo.Activity;
import com.newer.mall.common.pojo.Brand;
import com.newer.mall.common.pojo.CartItem;
import com.newer.mall.common.pojo.Category;
import com.newer.mall.common.pojo.Comment;
import com.newer.mall.common.pojo.Commodity;

@Service
public interface CommodityService {

	/**
	 * 查看属于该类别的商品
	 * 
	 * @param pageNum    查询的页码
	 * @param categoryid 类别的id
	 * @return
	 */
	public PageInfo<Commodity> displayCommodityCategory(int pageNum, int categoryid);

	/**
	 * 查询推荐商品
	 * 
	 * @param pageNum 查询的页码
	 * @return
	 */
	public PageInfo<Commodity> displayCommodityRecommed(int pageNum);

	/**
	 * 查看活动
	 * 
	 * @param pageNum 查询的页码
	 * @return
	 */
	public PageInfo<Activity> queryCommodityActivity(int pageNum);

//	/**
//	 * 查看限时活动
//	 * 
//	 * @param pageNum 查看页码
//	 * @return
//	 */
//	public PageInfo<Activity> queryCommodityDiscount(int pageNum);

	/**
	 * 查看该品牌对应的商品
	 * 
	 * @param pageNum查询页码
	 * @param brandid
	 * @return
	 */
	public PageInfo<Commodity> queryCommodityByBrand(int pageNum, int brandid);

	/**
	 * 根据关键字查询商品
	 * 
	 * @param pageNum            查看页码
	 * @param commodityname商品关键字
	 * @return
	 */
	public PageInfo<Commodity> queryCommodityByName(int pageNum, String commodityname);

	/**
	 * 商品详情
	 * 
	 * @param commodityid 商品关键字
	 * @return
	 */
	public Commodity queryCommodity(int commodityid);

	/**
	 * 添加购物车
	 * 
	 * @param commodityid添加商品的id
	 * @param quantity添加的数量
	 * @param param商品参数
	 */
	public List<CartItem> addCart(int uid,int commodityid, int quantity, String param);

	/**
	 * 查看商品评论
	 * @param pageNum	查看页码
	 * @param commodityid商品id
	 * @return
	 */
	public PageInfo<Comment> queryCommoditycomment(int pageNum, int commodityid);

	/**
	 * 显示所有品牌
	 * @return
	 */
	public List<Brand> showBrand();

	
	/**
	 * 显示所有类别
	 * @return
	 */
	public List<Category> showCategory();

}
