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

import com.newer.mall.common.pojo.CartItem;
import com.newer.mall.common.pojo.Comment;
import com.newer.mall.common.pojo.Item;
import com.newer.mall.common.pojo.Orders;

/**
  *  订单mapper
 * @author LiuLinJie
 *
 */
public interface CustomerOrderMapper {
    // 插入订单
	@Insert("insert into orders(uid,name,phone,address,payway,total)  "
			+ "value(#{orders.customer.id},#{orders.name},"
			+ "#{orders.name},#{orders.phone},"
			+ "#{orders.address},#{orders.total})")
	public void addOrder(Orders orders);
	
	// 插入订单项
	@Insert("insert into item() value (#{oid},#{cid},#{param},#{qtity},#{remark})")
	public void addItme(@Param("cid")int cid,@Param("oid")int oid, 
			            @Param("param")String param , 
			            @Param("qtity")int quantity ,
			            @Param("remark")String remark );
	
	//查询该用户最新生成的订单id
	@Select("select max(id) from orders where uid =#{uid}")
	public int findoid(@Param("uid")int uid);
	
	// 查询订单,sendstatus 订单状态
	@Select("select * from orders where uid =#{uid} and sendstatus =#{sendstatus} ")
	@Results(
			{
				@Result(
						column = "id",
						property = "items",
						javaType = com.newer.mall.common.mapper.CustomerOrderMapper.class,
						many = @Many(select = "finditems")					
						)
			}
			)
	public List<Orders> findOrders(int uid,int sendstatus);
	
	//查询订单项
	@Select("select*from item where order_id =#{oid}")
	@Results(
			{
				@Result(
						column = "commodity_id",
						property = "commodity",
						javaType = com.newer.mall.common.mapper.CommodityMapper.class,
						one = @One(select = "selectCommodity")
						)
			}
			)
	public List<Item> finditems(@Param("oid")int oid);
	
	//搜索订单
	@Select("select * from orders where uid =#{uid}")
	@Results(
			{
				@Result(
						column = "id",
						property = "items",
						javaType = com.newer.mall.common.mapper.CustomerOrderMapper.class,
						many = @Many(select = "finditems")
						)
			}
			)
	public List<Orders> serachOrders(@Param("uid")int uid , String conditions);
	
	
	//修改hidden字段,已完成删除订单
	@Update("update orders set hidden = 1 where id = #{oid}")
	public void updtOrder(@Param("oid")int oid);
	
	
	//插入评论
	@Insert("insert into comment(commodity_id,uid,content,score) value("
			                                                            + "#{comment.commodity.id},"
			                                                            + "#{comment.customer.id},"
			                                                            + "#{comment.content},"
			                                                            + "#{comment.score} ) ")
	public void addComment (Comment comment);
	
	//查看商品库存
	@Select("select stock from commodity where id=#{cid}")
	public int findstock(@Param("cid")int cid);
	
	
	//修改订单状态
	@Update("update orders set sendstatus=#{sendstatus} where id =#{oid}")
	public void upsendstatus(@Param("oid")int oid ,@Param("sendstatus") int sendstatus); 
}
