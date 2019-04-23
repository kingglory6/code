package com.newer.mall.common.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.newer.mall.common.pojo.Activity;

public interface ActivityMangeMapper {

	// 添加活动
	@Insert("insert into activity(commodity_id,start_time,end_time,stock,price,type) values(#{commodity.id},#{startTime},#{endTime},#{stock},#{price},#{type})")
	public void addActivity(Activity activity);

	@Select("select Last_insert_id()")
	public int getActivityId();

	// 开始活动
	@Update("update commodity set discount = #{price},stock = #{stock},activity = 1 where id = #{commodity.id}")
	public void activitySpikeStrat(Activity act);

	// 开始活动
	@Update("update commodity set discount = #{price},activity = 1 where id = #{commodity.id}")
	public void activityDiscountStrat(Activity act);

	// 活动结束
	@Update("update commodity set discount = price,stock = limitstock,activity = 0 where id = #{id}")
	public void activityEnd(@Param("id") int id);

	// 活动状态
	@Update("update activity set statu = 1 where id = #{id}")
	public void activityStatu(@Param("id") int id);

	// 获得活动列表
	@Select("select * from activity where statu = 0")
	@Results({ @Result(property = "startTime", column = "start_time"),
			@Result(property = "endTime", column = "end_time"),
			@Result(property = "commodity", column = "commodity_id", one = @One(select = "com.newer.mall.common.mapper.CommodityMangeMapper.getCommodity")) })
	public List<Activity> getActivityList();

	// 删除活动
	@Delete("delete from activity where id = #{id}")
	public void deleteActivity(@Param("id") int id);

	// 查询单个活动
	@Select("select * from activity where id = #{id}")
	@Results({ @Result(property = "startTime", column = "start_time"),
			@Result(property = "endTime", column = "end_time"),
			@Result(property = "commodity", column = "commodity_id", one = @One(select = "com.newer.mall.common.mapper.CommodityMangeMapper.getCommodity")) })
	public Activity getActivity(@Param("id") int id);

}
