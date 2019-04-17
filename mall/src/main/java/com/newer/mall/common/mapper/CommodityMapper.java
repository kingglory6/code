package com.newer.mall.common.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.newer.mall.common.pojo.Activity;
import com.newer.mall.common.pojo.Brand;
import com.newer.mall.common.pojo.Category;
import com.newer.mall.common.pojo.Comment;
import com.newer.mall.common.pojo.Commodity;

@Mapper
public interface CommodityMapper {

	/**
	 * 查询该类别商品信息
	 * 
	 * @param categoryid
	 * @return
	 */
	@Select("select * from commodity where category_id=#{categoryid}")
	@Results({
			@Result(column = "brand_id", property = "brand", javaType = com.newer.mall.common.pojo.Brand.class, one = @One(select = "selectBrand")),
			@Result(column = "category_id", property = "category", javaType = com.newer.mall.common.pojo.Category.class, one = @One(select = "selectCategory")) })
	public List<Commodity> selectCommodityCategory(@Param("categoryid") int categoryid);

	/**
	 * 查询推荐商品
	 * 
	 * @return
	 */
	@Select("select * from commodity where recommed=1")
	@Results({
			@Result(column = "brand_id", property = "brand", javaType = com.newer.mall.common.pojo.Brand.class, one = @One(select = "selectBrand")),
			@Result(column = "category_id", property = "category", javaType = com.newer.mall.common.pojo.Category.class, one = @One(select = "selectCategory")) })
	public List<Commodity> selectCommodityRecommed();

	/**
	 * 查询限量折扣商品
	 * 
	 * @return
	 */
	@Select("select * from spike")
	@Results({
			@Result(column = "commodity", property = "commodity", javaType = com.newer.mall.common.pojo.Commodity.class, one = @One(select = "selectCommodity")) })
	public List<Activity> selectCommoditySpike();

	/**
	 * 查询限时折扣商品
	 * 
	 * @return
	 */
	@Select("select * from discount")
	@Results({
			@Result(column = "commodity", property = "commodity", javaType = com.newer.mall.common.pojo.Commodity.class, one = @One(select = "selectCommodity")) })
	public List<Activity> selectCommodityDiscount();

	/**
	 * 查询该品牌的所有商品
	 * 
	 * @param brandid
	 * @return
	 */
	@Select("select * from commodity where brand_id=#{brandid}")
	public List<Commodity> selectCommodityByBrand(@Param("brandid") int brandid);

	/**
	 * 根据关键字查询
	 * 
	 * @return
	 */
	@Select("select * from commodity where title like %${name}%")
	public List<Commodity> selectCommodityByName(@Param("name") String name);

	/**
	 * 查询商品详细信息
	 * 
	 * @param Commodityid
	 * @return
	 */
	@Select("select * from commodity where id=#{commodityid}")
	@Results({
			@Result(column = "brand_id", property = "brand", javaType = com.newer.mall.common.pojo.Brand.class, one = @One(select = "selectBrand")),
			@Result(column = "category_id", property = "category", javaType = com.newer.mall.common.pojo.Category.class, one = @One(select = "selectCategory")) })
	public Commodity selectCommodity(@Param("commodityid") int commodityid);

	/**
	 * 添加入购物车表
	 * 
	 * @param uid
	 * @param commodityid
	 * @param quantity
	 * @param param
	 */
	@Insert("insert into cart value(#{uid},#{commodityid},#{quantity},#{specid})")
	public void addCart(@Param("uid") int uid, @Param("commodityid") int commodityid, @Param("quantity") int quantity,
			@Param("specid") int specid);

	/**
	 * 查看商品评论
	 * 
	 * @param commodityid
	 * @return
	 */
	@Select("select * from comment where commodityid=#{commodityid}")
	@Results({
			@Result(column = "uid", property = "customer", javaType = com.newer.mall.common.pojo.Customer.class, one = @One(select = "showCustById")),
			@Result(column = "commodity", property = "commodity", javaType = com.newer.mall.common.pojo.Commodity.class, one = @One(select = "selectCommodity"))

	})
	public List<Comment> selectCommoditycomment(int commodityid);

	/**
	 * 查询品牌信息
	 * 
	 * @param brandid
	 * @return
	 */
	@Select("select * from brand where id=#{brandid}")
	public Brand selectBrand(int brandid);

	/**
	 * 查看类别信息
	 * 
	 * @param categoryid
	 * @return
	 */
	@Select("select * from category where id=#{categoryid}")
	public Category selectCategory(int categoryid);

	/**
	 * 查询参数id
	 * 
	 * @param param
	 * @return
	 */
	@Select("selet id from spec where param=#{param}")
	public int selectSpec(String param);
}
