package com.newer.mall.common.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.github.pagehelper.Page;
import com.newer.mall.common.pojo.Brand;
import com.newer.mall.common.pojo.Category;
import com.newer.mall.common.pojo.Commodity;
import com.newer.mall.common.pojo.Notice;
import com.newer.mall.common.pojo.Spec;

@Mapper
public interface CommodityMangeMapper {

	// 查询商品列表
	@Select("select * from Commodity")
	@Results({
			@Result(property = "category", column = "category_id", one = @One(select = "com.newer.mall.common.mapper.CommodityMangeMapper.getCategory")),
			@Result(property = "brand", column = "brand_id", one = @One(select = "com.newer.mall.common.mapper.CommodityMangeMapper.getBrand")) })
	public List<Commodity> getCommodityAll();

	@Select("select * from category where id = #{id}")
	public Category getCategory(@Param("id") int id);

	@Select("select * from brand where id = #{id}")
	public Brand getBrand(@Param("id") int id);
	
	@Select("select Last_insert_id()")
	public int getId();

	// 添加商品
	@Insert("insert into Commodity(title,price,discount,description,category_id,brand_id) values(#{title},#{price},#{price},#{description},#{category.id},#{brand.id})")
	public void addCommodity(Commodity com);

	@Delete("delete from commodity where id = #{id}")
	public void deleteCommodity(@Param("id") int id);

	// 上下架
	@Update("update commodity set shelf = #{type} where id = #{id}")
	public void updateShelf(@Param("id") int id, @Param("type") int type);

	// 查询通知列表
	@Select("select n.email,c.title from notice n left join commodity c on n.commodity_id = c.id and c.id = #{id}")
	public List<Notice> getEmail(@Param("id") int id);
	
	@Update("update notice set iNotice = 1")
	public void updateNotice(String email,int id);

	// 查询库存
	@Select("select stock from commodity where id = #{id}")
	public int getStock(@Param("id") int id);

	// 修改库存
	@Update("update commodity set stock = #{num},limitstock = #{num} where id = #{id}")
	public void updateStock(@Param("id") int id, @Param("num") int num);

	// 是否推荐
	@Update("update commodity set recommend = #{type} where id = #{id}")
	public void updateRecommend(@Param("id") int id, @Param("type") int type);

//	@Insert("insert into discount(commodity_id,start_time,end_time,price) values(#{commodity.id},#{startTime},#{endTime},#{price})")
//	public void addDiscountActivity(Activity activity);

	// 查询单个商品 
	@Select("select * from commodity where id = #{id}")
	public Commodity getCommodity(@Param("id") int id);

	// 修改商品信息
	@Update("update commodity set title = #{title},price=#{price},discount=#{price},description=#{description},stock=#{stock},limitstock=#{stock},category_id=#{category.id},brand_id=#{brand.id} where id = #{id}")
	public void updateCommodity(Commodity com);

	// 添加单个规格
	@Insert("insert into spec(commodity_id,param,img) values(#{commodity.id},#{param},#{img})")
	public void addSpec(Spec spec);

	// 插入多个规格
	@Insert({ "<script>", "insert into spec(commodity_id,param,img) values ",
			"<foreach collection='spec' item='s' index='index' separator=','>",
			"(#{id}, #{s.param}, #{s.img})", "</foreach>", "</script>" })
	public void addSpecList(@Param("id")int id,@Param("spec") List<Spec> spec);

	// 删除规格
	@Delete("delete from spec where id = #{id}")
	public void deleteSpec(@Param("id") int id);

	// 分类
	@Select("select * from category")
	@Results({
			@Result(property="id",column="id"),
			@Result(property = "commodityList", column = "id", many = @Many(select = "com.newer.mall.common.mapper.CommodityMangeMapper.getCategoryComm")) })
	public List<Category> getCategorys();

	@Select("select * from commodity where category_id = #{id}")
	public List<Commodity> getCategoryComm(@Param("id") int id);

	// 品牌
	@Select("select * from brand")
	@Results({
			@Result(property="id",column="id"),
			@Result(property = "commodityList", column = "id", many = @Many(select = "com.newer.mall.common.mapper.CommodityMangeMapper.getBrandComm")) })
	public List<Brand> getBrands();

	@Select("select * from commodity where brand_id = #{id}")
	public List<Commodity> getBrandComm(@Param("id") int id);

	// 以上下架商品
//	@Select("select * from Commodity where shelf = #{shelf}")
//	@Results({
//		@Result(property="category",column="category_id",one=@One(select="com.newer.mall.common.mapper.CommodityMangeMapper.getCategory")),
//		@Result(property="brand",column="brand_id",one=@One(select="com.newer.mall.common.mapper.CommodityMangeMapper.getBrand"))
//	})
//	public List<Commodity> getUpCommodity(@Param("shelf") int shelf);

	@Select({ "<script>",
			"select * from commodity <if test='shelf!=-1 or cid!=-1 or bid!=-1 or text!=null'>where</if> <if test='shelf!=-1'>shelf = #{shelf} </if> <if test='shelf!=-1 and cid != -1'> and </if>  <if test='cid!=-1'>category_id = #{cid}</if> <if test='cid!=-1 and bid!=-1'>and</if> <if test='bid!=-1'>brand_id = #{bid}</if> <if test='text!=null and bid!=-1'>and</if> <if test='text!=null'>CONCAT(`title`,`description`) like '%${text}%'</if>",
			"</script>" })
	@Results({
	@Result(property = "category", column = "category_id", one = @One(select = "com.newer.mall.common.mapper.CommodityMangeMapper.getCategory")),
	@Result(property = "brand", column = "brand_id", one = @One(select = "com.newer.mall.common.mapper.CommodityMangeMapper.getBrand")) })
	public Page<Commodity> conditionalQuery(@Param("shelf") int shelf, @Param("cid") int cid, @Param("bid") int bid,@Param("text") String text);
	
	@Insert("insert into category(name) values(#{name})")
	public void addCategory(Category category);
	
	@Insert("insert into brand(name) values(#{name})")
	public void addBrand(Brand brand);
	
	
	@Delete("delete from category where id = #{id}")
	public void deleteCategory(@Param("id") int id);
	
	@Delete("delete from brand where id = #{id}")
	public void deleteBrand(@Param("id") int id);
	
	@Select("select count(id) from commodity where category_id = #{id}")
	public int getCategoryNumber(@Param("id") int id);
	
	@Select("select count(id) from commodity where brand_id = #{id}")
	public int getBrandNumber(@Param("id") int id);
}
