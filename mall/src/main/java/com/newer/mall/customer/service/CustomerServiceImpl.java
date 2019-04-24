package com.newer.mall.customer.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.newer.mall.common.exception.PasswordErrorException;
import com.newer.mall.common.exception.RegisterException;
import com.newer.mall.common.mapper.CustomerMapper;
import com.newer.mall.common.pojo.Address;
import com.newer.mall.common.pojo.Collection;
import com.newer.mall.common.pojo.Customer;
import com.newer.mall.common.pojo.History;
import com.newer.mall.common.utils.JwtUtil;
import com.newer.mall.common.utils.Password;

@Service
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	CustomerMapper custmapper;
	
	/**
	 * 注册
	 */
	@Override
	public void register(Customer customer) throws RegisterException{
		//判断邮箱是否被注册
		Customer user = custmapper.showCust(customer.getEmail());
		if(user==null) {
			//密码加密
			String newPWD = Password.toSHA2(customer.getPassword());
			customer.setPassword(newPWD);
			custmapper.customer(customer);
		}else {
			throw new RegisterException("邮箱已被注册");
		}
	}

	/**
	 * 用户登录
	 * return true 登录成功，false登录失败
	 */
	@Override
	public Customer login(HttpSession session,String email, String password)throws PasswordErrorException {
		String pwd = custmapper.login(email);
		password=Password.toSHA2(password);
		boolean flag=pwd.equals(password);
		if(flag) {
			Customer cust=custmapper.showCust(email);
			cust.setToken(JwtUtil.getToken(email));
			session.setAttribute("uid", cust.getId());
			session.setAttribute("customer", cust);
			return cust;
		}else {		
			throw new PasswordErrorException();
		}
	}

	/**
	 * 修改信息
	 */
	@Override
	public boolean info(Customer customer) {
		custmapper.update(customer);
		System.out.println(customer);
		return true;
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
	public PageInfo<Collection> showCollection(int uid,int pageNum) {
		 
		PageHelper.startPage(pageNum, 10);
		PageInfo<Collection> page = new PageInfo<>(custmapper.showCollection(uid));
		
		return  page ;
	}

}
