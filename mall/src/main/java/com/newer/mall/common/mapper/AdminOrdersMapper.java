package com.newer.mall.common.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.newer.mall.common.pojo.Customer;
import com.newer.mall.common.pojo.Item;
import com.newer.mall.common.pojo.Orders;

@Mapper
public interface AdminOrdersMapper {
	
	//查询订单列表
	@Select({ "<script>",
		"select * from orders <if test='wl!=null or name!=null or paystatu!=-1 or payway!=-1 or send!=-1'>where</if> <if test='wl!=null'>wuliu like '%${wl}%' </if> <if test='wl!=null and name != null'> and </if>  <if test='name!=null'>name like '%${name}%'</if> <if test='name!=null and paystatu!=-1'>and</if> <if test='paystatu!=-1'>paystatus = #{paystatu}</if> <if test='paystatu!=-1 and payway!=-1'>and</if> <if test='payway!=-1'>payway=#{payway}</if> <if test='payway!=-1 and send!=-1'>and</if> <if test='send!=-1'>sendstatus=#{send}</if> ",
		"</script>" })
	@Results({
		@Result(property="id",column="id"),
		@Result(property="item",column="id",many=@Many(select="getItems")),
		@Result(property="customer",column="uid",one=@One(select="getCastomer"))
	})
	public List<Orders> getOrders(@Param("wl")String wl,@Param("name")String name,@Param("paystatu")int paystatu,@Param("payway")int payway,@Param("send")int send);
	
	//查询订单项
	@Select("select * from item where order_id = #{id}")
	@Results({
		@Result(property="commodity",column="commodity_id",one=@One(select="com.newer.mall.common.mapper.CommodityMangeMapper.getCommodity")),
		@Result(property="spec.param",column="param")
	})
	public List<Item> getItems(@Param("id") int id);
	
	
	//查询客户
	@Select("select * from customer where id = #{id}")
	@Results({
		@Result(property="name",column="netname")
	})
	public Customer getCastomer(@Param("id") int id);
	
	//订单信息修改
	@Update("update orders set name = #{name},phone=#{phone},address=#{address} where id = #{id}")
	public void updateOrders(Orders order);
	
	
	//发货
	@Update("update orders set sendstatus = 1,wuliu = #{wuliu} where id = #{id}")
	public void updateSendStatu(@Param("id")int id,@Param("wuliu")String wuliu);
	
	//删除订单
	@Delete("delete from orders where id = #{id}")
	public void deleteOrder(@Param("id") int id);
}
