package com.newer.mall.common.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.newer.mall.common.pojo.Address;
import com.newer.mall.common.pojo.Collection;
import com.newer.mall.common.pojo.Commodity;
import com.newer.mall.common.pojo.Customer;
import com.newer.mall.common.pojo.History;

@Repository
@Mapper
public interface CustomerMapper {
	
	/**
	 * 注册用户
	 * @param customer
	 */
	@Insert("insert into customer(netname,phone,email,password) value(#{name},#{phone},#{email},#{password})")
	public void customer (Customer customer);
	
	/**
	 * 查询用户密码
	 * @param email
	 * @return
	 */
	@Select("select password from customer where email=#{email}")
	public String login(@Param("email") String email);
	
	/**
	 * 查看用户基本信息
	 * @param email
	 * @return
	 */
	@Select("select id,netname,phone,email from customer where email=#{email}")
	@Results({
		@Result(column="netname",property="name")
	})
	public Customer showCust(@Param("email") String email);
	
	/**
	 * 根据uid 查用户基本信息
	 * @param uid
	 * @return
	 */
	@Select("select netname,phone,email from customer where id=#{uid}")
	public Customer showCustById(@Param("uid") int uid);
	/**
	 * 修改用户信息
	 * @param phone
	 * @param netname
	 */
	@Update("update customer set netname=#{name},phone=#{phone} where email=#{email}")
	public void update(Customer customer);
	
	/**
	 * 添加新收货地址
	 * @param address
	 */
	@Insert("insert into address(name,phone,address,uid) value(#{address.name},#{address.phone},#{address.address},#{uid})")
	public void addAddress(@Param("uid")int uid,@Param("address")Address address);
	
	/**
	 * 查看用户所有收货地址
	 * @param uid
	 * @return
	 */
	@Select("select * from address where uid=#{uid}")
	public List<Address> showAddress(@Param("uid") int uid);
	
	/**
	 * 添加用户足迹
	 * @param id
	 */
	@Insert("insert into history(uid,commodity_id) value(#{uid},#{history.commodity.id})")
	public  void  foot (@Param("uid") int uid,History history);
	
	/**
	 * 查看用户足迹
	 * @param id
	 * @return
	 */
	@Select("select * from history")
	@Results({
		@Result(column="commodity_id",property="commodity",
				javaType=com.newer.mall.common.pojo.Commodity.class,
				one=@One(select="com.newer.mall.common.mapper.CommodityMapper.selectCommodity")),
		@Result(column="datetime",property="time")
	})
	public List <History> showFoot (int uid);
	
	/**
	 * 收藏商品	
	 * @param id
	 */
	@Insert("insert into collection(uid,commodity_id,price) value(#{uid},#{collection.commodity.id},#{collection.price})")
	public void  collection (@Param("uid") int uid,Collection collection);
	
	/**
	 * 查看收藏商品
	 * @param id
	 * @return
	 */
	@Select("select * from collection where uid=#{uid}")
	@Results({
		@Result(column="commodity_id",property="commodity",
				javaType=com.newer.mall.common.pojo.Commodity.class,
				one=@One(select="com.newer.mall.common.mapper.CommodityMapper.selectCommodity"))
	})
	public List <Collection> showCollection (int uid);
}
