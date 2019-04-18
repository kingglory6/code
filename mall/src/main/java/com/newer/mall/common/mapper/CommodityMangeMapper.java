package com.newer.mall.common.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.newer.mall.common.pojo.Activity;
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
	
	@Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
	@Insert("insert into activity(commodity_id,start_time,end_time,stock,price,type) values(#{commodity.id},#{startTime},#{endTime},#{stock},#{price},#{type})")
	public int addActivity(Activity activity);
	
//	@Insert("insert into discount(commodity_id,start_time,end_time,price) values(#{commodity.id},#{startTime},#{endTime},#{price})")
//	public void addDiscountActivity(Activity activity);
	
	@Update("update commodity set discount = #{price},stock = #{stock},activity = 1 where id = #{commodity.id}")
	public void activitySpikeStrat(Activity act);
	
	@Update("update commodity set discount = #{price},activity = 1 where id = #{commodity.id}")
	public void activityDiscountStrat(Activity act);
	
	@Update("update commodity set discount = price,stock = limitstock,activity = 0 where id = #{id}")
	public void activityEnd(@Param("id") int id);
	
	@Update("update activity set statu = 1 where id = #{id}")
	public void activityStatu(@Param("id") int id);
	
	@Select("select * from activity where statu = 0")
	@Results({
		@Result(property="startTime",column="start_time"),
		@Result(property="endTime",column="end_time"),
		@Result(property="commodity",column="commodity_id",one=@One(select="getCommodity"))
	})
	public List<Activity> getActivity();
	
	@Select("select * from commodity where id = #{id}")
	public Commodity getCommodity(@Param("id") int id);

}
