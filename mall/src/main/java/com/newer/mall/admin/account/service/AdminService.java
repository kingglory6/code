package com.newer.mall.admin.account.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newer.mall.common.exception.AccountNotFound;
import com.newer.mall.common.exception.PasswordError;
import com.newer.mall.common.mapper.AdminAccountMapper;
import com.newer.mall.common.pojo.Admin;
import com.newer.mall.common.utils.Password;

@Service
public class AdminService {
	
	@Autowired
	AdminAccountMapper adminMapper;
	
	/**
	 * 管理员登陆
	 * @param account
	 * @param password
	 * @throws AccountNotFound 
	 * @throws PasswordError 
	 */
	public Admin login(String account,String password) throws AccountNotFound, PasswordError {
		Admin admin = adminMapper.login(account);
		if(admin == null) {
			throw new AccountNotFound();
		}else if(!admin.getPassword().equals(Password.toSHA2(password))) {
			throw new PasswordError();
		}
		return admin;
		
	}
}
