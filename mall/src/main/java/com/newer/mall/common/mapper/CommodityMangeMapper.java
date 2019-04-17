package com.newer.mall.common.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.newer.mall.common.pojo.Commodity;

@Mapper
public interface CommodityMangeMapper {
	
	@Select("select * from Commodity")
	public List<Commodity> getCommodityAll();
	
	
	@Insert("insert into Commodity(title,price,description,category_id,brand_id) values(#{title},#{price},#{description},#{category.id},#{brand.id})")
	public void addCommodity(Commodity com);
	
	@Update("update commodity set shelf = #{type} where id = #{id}")
	public void updateShelf(@Param("id") int id,@Param("type") int type);
	

}
