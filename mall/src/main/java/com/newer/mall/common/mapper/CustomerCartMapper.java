package com.newer.mall.common.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.newer.mall.common.pojo.CartItem;

/**
  *  用户购物车mapper
 * @author LiuLinJie
 *
 */
@Mapper
public interface CustomerCartMapper {
	
    //查询用户购物车
	@Select("select * from cart where uid = #{uid} ")
	@Results(
			{
				@Result(
						column = "commodity_id",
						property = "commodity",
						javaType = com.newer.mall.common.mapper.CustomerCartMapper.class,
						one = @One(select = "")
						),
				@Result(
						column = "spec_id",
						property = "spec",
						javaType = com.newer.mall.common.mapper.CustomerCartMapper.class,
						one = @One(select = "")
						)
			}
			)
	public List<CartItem> checkCart(@Param("uid") int uid);
	
	//修改数量
	@Update("update cart set quantity = quantity + #{quantity} where uid = #{uid} and commodity_id = #{cid} and spec_id = #{sid}")
    public void changeQuantity(@Param("uid") int uid ,
    		                   @Param("cid") int cid ,
    		                   @Param("sid") int sid ,
    		                   @Param("quantity") int quantity);
	
	//删除购物车项
	@Delete("delete from cart where uid = #{uid} and commodity_id = #{cid} and spec_id = #{sid}")
	public void dltCart(@Param("uid") int uid ,
			            @Param("cid") int cid ,
			            @Param("sid") int sid);
	
	
	//根据id 查询购物车项
	@Select("")
    public List<CartItem> fcart(int uid,int cid);
	
	//根据条件搜索购物车项
	@Select("")
	public List<CartItem>  findcart(int uid , String conditions);
	
	
}
