package com.newer.mall.commodity.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.newer.mall.common.mapper.CommodityMapper;
import com.newer.mall.common.mapper.CustomerCartMapper;
import com.newer.mall.common.pojo.Activity;
import com.newer.mall.common.pojo.Brand;
import com.newer.mall.common.pojo.CartItem;
import com.newer.mall.common.pojo.Category;
import com.newer.mall.common.pojo.Comment;
import com.newer.mall.common.pojo.Commodity;

@Service
public class CommodityServiceImpl implements CommodityService {

	@Autowired
	CommodityMapper commapper;
	@Autowired
	CustomerCartMapper cartmapper;
	/**
	 * 显示该类别下所有商品
	 */
	@Override
	public PageInfo<Commodity> displayCommodityCategory(int pageNum, int categoryid) {
		// 设置分页信息
		PageHelper.startPage(pageNum, 10);
		List<Commodity> commlist = commapper.selectCommodityCategory(categoryid);
		PageInfo<Commodity> pageinfo = new PageInfo<>(commlist);

		return pageinfo;
	}

	/**
	 * 显示推荐商品
	 */
	@Override
	public PageInfo<Commodity> displayCommodityRecommed(int pageNum) {
		// 设置分页信息
		PageHelper.startPage(pageNum, 10);
		List<Commodity> commlist = commapper.selectCommodityRecommed();
		PageInfo<Commodity> pageinfo = new PageInfo<>(commlist);

		return pageinfo;
	}

	/**
	 * 显示限量折扣商品
	 */
	@Override
	public PageInfo<Activity> queryCommodityActivity(int pageNum) {
		// 设置分页信息
		PageHelper.startPage(pageNum, 10);
		List<Activity> commlist = commapper.selectCommodityActivity();
		PageInfo<Activity> pageinfo = new PageInfo<>(commlist);

		return pageinfo;
	}

//	/**
//	 * 显示限时折扣商品
//	 */
//	@Override
//	public PageInfo<Activity> queryCommodityDiscount(int pageNum) {
//		// 设置分页信息
//		PageHelper.startPage(pageNum, 10);
//		List<Activity> commlist = commapper.selectCommodityDiscount();
//		PageInfo<Activity> pageinfo = new PageInfo<>(commlist);
//
//		return pageinfo;
//	}

	/**
	 * 显示该品牌下所有商品
	 */
	@Override
	public PageInfo<Commodity> queryCommodityByBrand(int pageNum, int brandid) {
		// 设置分页信息
		PageHelper.startPage(pageNum, 10);
		List<Commodity> commlist = commapper.selectCommodityByBrand(brandid);
		PageInfo<Commodity> pageinfo = new PageInfo<>(commlist);

		return pageinfo;
	}

	/**
	 * 根据关键字查询商品
	 */
	@Override
	public PageInfo<Commodity> queryCommodityByName(int pageNum, String commodityname) {
		// 设置分页信息
		PageHelper.startPage(pageNum, 10);
		List<Commodity> commlist = commapper.selectCommodityByName(commodityname);
		PageInfo<Commodity> pageinfo = new PageInfo<>(commlist);

		return pageinfo;
	}

	/**
	 * 查看商品详细信息
	 */
	@Override
	public Commodity queryCommodity(int commodityid) {
		Commodity commodity = commapper.selectCommodity(commodityid);
		return commodity;
	}

	/**
	 * 商品添加入购物车
	 * @return 
	 */
	@Override
	public List<CartItem> addCart(int uid,int commodityid, int quantity, String param) {
		List<CartItem> fcart = cartmapper.fcart(uid,commodityid);
		//判断是否有重复商品的标记
		boolean flag=false;
		//记录重复商品索引
		int index=-1;
		//购物车中是否有同种商品
		if(fcart.isEmpty()){
			commapper.addCart(uid, commodityid, quantity, commapper.selectSpec(param,commodityid));
		}else {
			for(CartItem c:fcart) {
				 String spec = c.getSpec().getParam();
				 System.out.println(spec);
				 index++;
				//同种商品是否有同种规格
				 
				if(spec.equals(param)&&c.getCommodity().getId()==commodityid) {
					System.out.println(c.getCommodity().getId());
					flag=true;
					break;
					//cartmapper.changeQuantity(uid, commodityid, c.getSpec().getId(),c.getQuantity()+quantity);
				}
//				else {
//					//commapper.addCart(uid, commodityid, quantity, commapper.selectSpec(param));
//				}
			}
			if(flag) {
				cartmapper.changeQuantity(uid, commodityid, fcart.get(index).getSpec().getId(),fcart.get(index).getQuantity()+quantity);
			}else {
				commapper.addCart(uid, commodityid, quantity, commapper.selectSpec(param,commodityid));
			}
			
		}
			
		return cartmapper.checkCart(uid);
	}

	/**
	 * 查看商品评论
	 */
	@Override
	public PageInfo<Comment> queryCommoditycomment(int pageNum, int commodityid) {
		// 设置分页信息
		PageHelper.startPage(pageNum, 10);
		List<Comment> commlist = commapper.selectCommoditycomment(commodityid);
		PageInfo<Comment> pageinfo = new PageInfo<>(commlist);

		return pageinfo;
	}
	
	/**
	 * 显示所有类别
	 */
	@Override
	public List<Category> showCategory() {
		return commapper.selectAllCategory();
	}

	/**
	 * 显示所有品牌
	 */
	@Override
	public List<Brand> showBrand() {
		return commapper.selectAllBrand();
	}
	@Override
	public PageInfo<Commodity> showAllCommodity(int pageNum){
		PageHelper.startPage(pageNum, 10);
		List<Commodity> commlist = commapper.selectAllCommodity();
		PageInfo<Commodity> pageinfo = new PageInfo<>(commlist);
		return pageinfo;
	}

}
