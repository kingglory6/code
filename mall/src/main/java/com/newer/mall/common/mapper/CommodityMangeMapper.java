package com.newer.mall.common.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.newer.mall.common.pojo.Commodity;
import com.newer.mall.common.pojo.Notice;

@Mapper
public interface CommodityMangeMapper {
	
	@Select("select * from Commodity")
	public List<Commodity> getCommodityAll();
	
	
	@Insert("insert into Commodity(title,price,description,category_id,brand_id) values(#{title},#{price},#{description},#{category.id},#{brand.id})")
	public void addCommodity(Commodity com);
	
	@Update("update commodity set shelf = #{type} where id = #{id}")
	public void updateShelf(@Param("id") int id,@Param("type") int type);
	
	@Select("select n.email,c.title from notice n left join commodity c on n.commodity_id = c.id and c.id = #{id}")
	public List<Notice> getEmail(@Param("id") int id);
	
	@Select("select stock from commodity where id = #{id}")
	public int getStock(@Param("id") int id);
	
	@Update("update commodity set stock = #{num} where id = #{id}")
	public void updateStock(@Param("id") int id,@Param("num") int num);
	
	@Update("update commodity set recommend = #{type} where id = #{id}")
	public void updateRecommend(@Param("id") int id,@Param("type") int type);
	

}
