package com.newer.mall.customer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.newer.mall.common.exception.RegisterException;
import com.newer.mall.common.mapper.CustomerMapper;
import com.newer.mall.common.pojo.Address;
import com.newer.mall.common.pojo.Collection;
import com.newer.mall.common.pojo.Customer;
import com.newer.mall.common.pojo.History;
import com.newer.mall.common.utils.Password;

public class CustomerServiceImpl implements CustomerService {
	@Autowired
	CustomerMapper custmapper;
	
	/**
	 * 注册
	 */
	@Override
	public void register(Customer customer) {
		//判断邮箱是否被注册
		Customer user = custmapper.showCust(customer.getEmail());
		if(user==null) {
			//密码加密
			String newPWD = Password.toSHA2(customer.getPassword());
			customer.setPassword(newPWD);
			custmapper.customer(customer);
		}else {
			new RegisterException("邮箱已被注册");
		}
	}

	/**
	 * 用户登录
	 */
	@Override
	public Customer login(String email, String password) {
		String customer = custmapper.login(email);
		boolean flag=customer.equals(password);
		if(flag) {
			return custmapper.showCust(email);
		}else {			
			return null;
		}
	}

	/**
	 * 修改信息
	 */
	@Override
	public Customer info(Customer customer) {
		custmapper.update(customer);

		return custmapper.showCust(customer.getEmail());
	}
	/**
	 * 添加地址
	 */
	@Override
	public List<Address> addAddress(int uid,Address address) {
		custmapper.addAddress(uid,address);
		return custmapper.showAddress(uid);

	}
	
	/**
	 * 显示用户收获地址
	 */
	@Override
	public List<Address> showAddresses(int uid) {
		return custmapper.showAddress(uid);
	}
	
	/**
	 * 添加用户足迹
	 */
	@Override
	public void foot(int uid,History history) {
		custmapper.foot(uid,history);

	}
	
	/**
	 * 显示用户所有足迹
	 */
	@Override
	public List<History> showFoot(int uid) {
		
		return custmapper.showFoot(uid);
	}
	
	/**
	 * 用户添加收藏
	 */
	@Override
	public void collection(int uid, Collection collection) {
		custmapper.collection(uid, collection);

	}

	@Override
	public List<Collection> showCollection(int uid) {
		
		return custmapper.showCollection(uid);
	}

}
