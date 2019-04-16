package com.newer.mall.customer.service;

import java.util.List;

import com.newer.mall.common.pojo.Commodity;
import com.newer.mall.common.pojo.Customer;

public interface CustomerService {
	/**
	 * 	注册方法
	 * @param customer新注册用户
	 * @return
	 */
	public Customer register(Customer customer);
	
	/**
	 * 	用户登录
	 * @param email 用户邮箱
	 * @param password	用户密码
	 * @return
	 */
	public boolean signin(String email,String password);
	
	/**
	 * 	编辑用户信息
	 */
	public void info(String netname,String phone);
	
	/**
	 * 	添加收货地址
	 * @param recipient 收件人信息
	 * @param phone	收件人电话
	 * @param address 收件人地址
	 */
	public void addAddress(String recipient,String phone,String address);
	
	/**
	 * 	添加用户足迹
	 * @param id  浏览商品id
	 */
	public void foot(int id);
	
	/**
	 * 	查看历史足迹
	 * @param id  用户id
	 * @return
	 */
	public List<Commodity> showFoot(int id);
	
	/**
	 * 	添加收藏商品
	 * @param id 商品id
	 * @param uid 用户id
	 */
	public void collection(int uid,int id);
	
	/**
	 * 	查看用户收藏列表
	 * @param id 用户id
	 * @return
	 */
	public List<Commodity> showCollection(int uid);
	
	
}
