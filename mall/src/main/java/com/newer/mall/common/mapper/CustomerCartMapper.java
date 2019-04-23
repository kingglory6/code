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
import com.newer.mall.common.pojo.Spec;

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
						javaType = com.newer.mall.common.pojo.Commodity.class,
						one = @One(select = "com.newer.mall.common.mapper.CommodityMapper.selectCommodity")
						),
				@Result(
						column = "spec_id",
						property = "spec",
						javaType = com.newer.mall.common.pojo.Spec.class,
						one = @One(select = "findSpec")
						)
			}
			)
	public List<CartItem> checkCart(@Param("uid") int uid);
	
	//查询购物车里的商品规格
	@Select("select * from spec where id =#{sid}")
	public Spec findSpec(@Param("sid") int sid);
	
	//修改数量
	@Update("update cart set quantity = #{quantity} where uid = #{uid} and commodity_id = #{cid} and spec_id = #{sid}")
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
	@Select("select * from cart where uid = #{uid} and commodity_id = #{cid}")
	@Results(
			{
				@Result(
						column = "commodity_id",
						property = "commodity",
						javaType = com.newer.mall.common.pojo.Commodity.class,
						one =@One(select = "com.newer.mall.common.mapper.CommodityMapper.selectCommodity")
						),
				@Result(
						column = "spec_id",
						property = "spec",
						javaType = com.newer.mall.common.pojo.Spec.class,
						one = @One(select = "findspec")
						)
			}
			)
    public List<CartItem> fcart(@Param("uid")int uid,@Param("cid")int cid);
	
	//根据条件搜索购物车项 
	@Select("select c.* , cdity.title from cart c , commodity cdity where c.commodity_id = cdity.id "
			                                                            + "and  cdity.title like '%${ctions}%' "
			                                                            + "and  c.uid =#{uid}")
	@Results(
			{
				@Result(
						column = "commodity_id",
						property = "commodity",
						javaType = com.newer.mall.common.pojo.Commodity.class,
						one =  @One(select = "com.newer.mall.common.mapper.CommodityMapper.selectCommodity")
						),
				@Result(
						column = "spec_id",
						property = "spec",
						javaType = com.newer.mall.common.pojo.Spec.class,
						one = @One(select = "findSpec")
						)
			}	
			)
	public List<CartItem>  findcart(@Param("uid")int uid ,@Param("ctions") String conditions);
	
	
}
