package com.newer.mall.common.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.newer.mall.common.pojo.Commodity;
import com.newer.mall.common.pojo.Notice;
import com.newer.mall.common.pojo.Spec;

@Mapper
public interface CommodityMangeMapper {

	// 查询商品列表
	@Select("select * from Commodity")
	public List<Commodity> getCommodityAll();

	// 添加商品
	@Insert("insert into Commodity(title,price,description,category_id,brand_id) values(#{title},#{price},#{description},#{category.id},#{brand.id})")
	public void addCommodity(Commodity com);

	@Delete("delete from commodity where id = #{id}")
	public void deleteCommodity(@Param("id") int id);

	// 上下架
	@Update("update commodity set shelf = #{type} where id = #{id}")
	public void updateShelf(@Param("id") int id, @Param("type") int type);

	// 查询通知列表
	@Select("select n.email,c.title from notice n left join commodity c on n.commodity_id = c.id and c.id = #{id}")
	public List<Notice> getEmail(@Param("id") int id);

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
			"(#{s.commodity.id}, #{s.param}, #{s.img})", "</foreach>", "</script>" })
	public void addSpecList(@Param("spec") List<Spec> spec);

	// 删除规格
	@Delete("delete from spec where id = #{id}")
	public void deleteSpec(@Param("id") int id);

}
