package com.newer.mall.common.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import com.newer.mall.common.pojo.Item;
import com.newer.mall.common.pojo.Orders;

/**
  *  订单mapper
 * @author LiuLinJie
 *
 */
public interface CustomerOrderMapper {
    // 插入订单
	@Insert("insert into orders(uid,name,phone,address,payway,total)"
			+ "value(#{uid},#{orders.name},#{orders.phone},#{orders.address},#{orders.payway},#{orders.total})")
	public void addOrder(@Param("uid")int uid,@Param("orders")Orders orders);
	
	// 插入订单项
	@Insert("insert into item() value (#{oid},#{cid},#{qtity},#{param},#{remark})")
	public void addItme(@Param("oid")int oid,
			            @Param("cid")int cid, 
			            @Param("param")String param , 
			            @Param("qtity")int quantity ,
			            @Param("remark")String remark );
	
	//查询该用户最新生成的订单id
	@Select("select max(id) from orders where uid =#{uid}")
	public int findoid(@Param("uid")int uid);
	
	// 查询订单,sendstatus 订单状态 payway 支付状态
	@Select("select * from orders where uid =#{uid} and sendstatus =#{sendstatus} and hidden =0  and paystatus =#{paystatus}")
	@Results(
			{
				@Result(
						column = "id",
						property = "item",
						javaType = java.util.List.class,
						many =	 @Many(select = "finditem")					
						),
//				@Result(
//						column =  "uid",
//						property = "customer",
//						javaType = com.newer.mall.common.pojo.Customer.class,
//						one =@One (select ="com.newer.mall.common.mapper.showCustById" )
//						),
				@Result(
						column = "id",
						property = "id"
						)
			}
			)
	public List<Orders> findOrders(@Param("uid")int uid,@Param("sendstatus")int sendstatus ,@Param("paystatus") int paystatus);
	
	
	//查询订单项
	@Select("select * from item where order_id =#{oid}")
	@Results(
			{
				@Result(
						column = "commodity_id",
						property = "commodity",
						javaType = com.newer.mall.common.pojo.Commodity.class,
						one = @One(select = "com.newer.mall.common.mapper.CommodityMapper.selectCommodity")
						)
			}
			)
	public List<Item> finditem(@Param("oid")int oid);
	
	//搜索订单
	@Select("select o.*,c.title from orders o , item i ,commodity c "
			                                           + "where i.commodity_id = c.id "
			                                           + "and o.id =i.order_id "
			                                           + "and o.uid = #{uid} "
			                                           + "and c.title like '%${citions}%'")
	@Results(
			{
				@Result(
						column = "id",
						property = "items",
						javaType = com.newer.mall.common.pojo.Item.class,
						many = @Many(select = "finditems")
						) ,
				@Result(
						column = "id",
						property = "id"
						)
			}
			)
	public List<Orders> serachOrders(@Param("uid")int uid ,
			                         @Param("citions") String conditions);
	 
	
	//修改hidden字段,已完成删除订单
	@Update("update orders set hidden = 1 where id = #{oid}")
	public void updtOrder(@Param("oid")int oid);
	
	
	//修改商品库存
	@Update("update commodity set stock = stock - #{qtity} where id =#{cid} ")
	public void upstock(@Param("cid")int cid,@Param("qtity") int quantity );
	
	
	//插入评论
	@Insert("insert into comment(commodity_id,uid,content,score) value("
			                                                            + "#{cid},"
			                                                            + "#{uid},"
			                                                            + "#{ctent},"
			                                                            + "#{score} ) ")
	public void addComment (@Param("uid")int uid ,
			                @Param("cid") int cid ,
			                @Param("ctent")String content,
			                @Param("score") int score);
	
	//查看商品库存
	@Select("select stock from commodity where id=#{cid}")
	public int findstock(@Param("cid")int cid);
	
	
	//修改订单状态
	@Update("update orders set sendstatus=#{sendstatus} where id =#{oid}")
	public void upsendstatus(@Param("oid")int oid ,@Param("sendstatus") int sendstatus); 
	
	//支付成功,修改支付状态
	@Update("update orders set payway=1 where uid =#{uid} and oid=#{oid} ")
	public void uppayway(@Param("uid")int uid ,@Param("oid") int oid);
	
	//查询当个订单
	@Select("select * from  orders where id =#{oid} ")
	@Results(
			{
			@Result(
					column = "id",
					property = "items",
					javaType = java.util.List.class,
					many =  @Many(select = "finditem")
					),	
			@Result(
					column = "id",
					property = "id"
					
					)
			} 
			)
	public Orders findOrder(@Param("oid")int oid);
	
	//查询已删除的订单
	@Select("select * from orders where uid =#{uid} and hidden =1")
	@Results(
			{
			@Result(
					column = "id",
					property = "item",
					javaType =  java.util.List.class,
					many = @Many(select = "finditem")
					),
			@Result(
					column = "id",
					property = "id"
					
					)
			}
			)
	public List<Orders> fdltOrders(@Param("uid")int uid);
	
}
