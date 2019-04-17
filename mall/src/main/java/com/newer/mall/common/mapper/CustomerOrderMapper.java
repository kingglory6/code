package com.newer.mall.common.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.newer.mall.common.pojo.CartItem;
import com.newer.mall.common.pojo.Comment;
import com.newer.mall.common.pojo.Orders;

/**
  *  订单mapper
 * @author LiuLinJie
 *
 */
public interface CustomerOrderMapper {
    // 插入订单
	@Insert("")
	public void addOrder(Orders orders,int uid);
	
	// 插入订单项
	@Insert("")
	public void addItme(CartItem cartItem,@Param("oid")int oid);
	
	//查询该用户最新生成的订单id
	@Select("")
	public int findoid(int uid);
	
	// 查询订单
	@Select("")
	public List<Orders> findOrders(int uid);
	
	
	//搜索订单
	@Select("")
	public List<Orders> serachOrders(int uid , String conditions);
	
	//修改hidden字段,已完成删除订单
	@Update("")
	public void updtOrder(int oid);
	
	
	//插入评论
	@Insert("")
	public void addComment (Comment comment);
	
	//查看商品库存
	@Select("")
	public int findstock(@Param("cid")int cid);
	
}
