package com.newer.mall.common.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
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
	@Select("")
	public List<CartItem> checkCart(int uid);
	
	//修改数量
	@Update("")
    public void changeQuantity(int uid , int cid , int sid , int quantity);
	
	//删除购物车项
	@Delete("")
	public void dltCart(int uid , int cid , int sid);
	
	
	//根据id 查询购物车项
	@Select("")
    public List<CartItem> fcart(int cid);
	
	//根据条件搜索购物车项
	@Select("")
	public List<CartItem>  findcart(int uid , String conditions);
	
	
}
