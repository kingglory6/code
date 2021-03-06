package com.newer.mall.customer.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.newer.mall.common.exception.PasswordErrorException;
import com.newer.mall.common.exception.RegisterException;
import com.newer.mall.common.pojo.Address;
import com.newer.mall.common.pojo.Collection;
import com.newer.mall.common.pojo.Customer;
import com.newer.mall.common.pojo.History;

@Service
public interface CustomerService {
	/**
	 * 	注册方法
	 * @param customer新注册用户
	 * @return
	 * @throws RegisterException 
	 */
	public void register(Customer customer) throws RegisterException;
	
	/**
	 * 	用户登录
	 * @param email 用户邮箱
	 * @param password	用户密码
	 * @return
	 */
	public Customer login(HttpSession session,String email,String password)throws PasswordErrorException;
	
	/**
	 * 	编辑用户信息
	 */
	public boolean info(Customer customer);
	
	/**
	 * 	添加收货地址
	 */
	public List<Address> addAddress(int uid,Address address);
	
	/**
	 * 显示用户所有收货地址
	 * @return
	 */
	public List<Address> showAddresses(int uid);
	
	/**
	 * 	添加用户足迹
	 * @param id  浏览商品id
	 */
	public void foot(int uid,History history);
	
	/**
	 * 	查看历史足迹
	 * @param id  用户id
	 * @return
	 */
	public List<History> showFoot(int uid);
	
	/**
	 * 	添加收藏商品
	 * @param uid 用户id
	 */
	public void collection(int uid,Collection collection);
	
	/**
	 * 	查看用户收藏列表
	 * @param id 用户id
	 * @return
	 */
	public PageInfo<Collection> showCollection(int uid,int pagenum);
	
	
}
