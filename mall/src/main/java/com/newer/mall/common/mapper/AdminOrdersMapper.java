package com.newer.mall.common.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.newer.mall.common.pojo.Orders;

@Mapper
public interface AdminOrdersMapper {
	
	@Select({ "<script>",
		"select * from orders <if test='wl!=null or name!=null or paystatu!=-1 or payway!=-1 or send!=-1'>where</if> <if test='wl!=null'>wuliu = #{wl} </if> <if test='wl!=null and name != null'> and </if>  <if test='name!=null'>name = #{name}</if> <if test='name!=null and paystatu!=-1'>and</if> <if test='paystatu!=-1'>paystatus = #{paystatu}</if> <if test='paystatu!=-1 and payway!=-1'>and</if> <if test='payway!=-1'>payway=#{payway}</if> <if test='payway!=-1 and send!=-1'>and</if> <if test='send!=-1'>sendstatus=#{send}</if> ",
		"</script>" })
	@Results({
		@Result(property="item")
	})
	public List<Orders> getOrders(@Param("wl")String wl,@Param("name")String name,@Param("paystatu")int paystatu,@Param("payway")int payway,@Param("send")int send);
	
	
	
}
