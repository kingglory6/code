package com.newer.mall.commodity.service;

import com.github.pagehelper.PageInfo;
import com.newer.mall.common.pojo.Comment;
import com.newer.mall.common.pojo.Commodity;

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
	 * 查看限量活动
	 * 
	 * @param pageNum 查询的页码
	 * @return
	 */
	public PageInfo<Commodity> queryCommoditySpike(int pageNum);

	/**
	 * 查看限时活动
	 * 
	 * @param pageNum 查看页码
	 * @return
	 */
	public PageInfo<Commodity> queryCommodityDiscount(int pageNum);

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
	 * @param pageNum     查看页码
	 * @param commodityid 商品关键字
	 * @return
	 */
	public Commodity queryCommodity(int pageNum, int commodityid);

	/**
	 * 添加购物车
	 * 
	 * @param commodityid添加商品的id
	 * @param quantity添加的数量
	 * @param param商品参数
	 */
	public void addCart(int commodityid, int quantity, String param);

	/**
	 * 查看商品评论
	 * @param pageNum	查看页码
	 * @param commodityid商品id
	 * @return
	 */
	public PageInfo<Comment> queryCommoditycomment(int pageNum, int commodityid);

}
