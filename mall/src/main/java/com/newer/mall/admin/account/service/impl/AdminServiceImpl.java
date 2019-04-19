package com.newer.mall.admin.account.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newer.mall.admin.account.service.AdminService;
import com.newer.mall.common.exception.AccountNotFoundException;
import com.newer.mall.common.exception.PasswordErrorException;
import com.newer.mall.common.mapper.AdminAccountMapper;
import com.newer.mall.common.pojo.Admin;
import com.newer.mall.common.utils.Password;

@Service
public class AdminServiceImpl implements AdminService{
	
	@Autowired
	AdminAccountMapper adminMapper;
	
	/**
	 * 管理员登陆
	 * @param account
	 * @param password
	 * @throws AccountNotFoundException 
	 * @throws PasswordErrorException 
	 */
	@Override
	public Admin login(String account,String password) throws AccountNotFoundException, PasswordErrorException {
		Admin admin = adminMapper.login(account);
		if(admin == null) {
			throw new AccountNotFoundException();
		}else if(!admin.getPassword().equals(Password.toSHA2(password))) {
			throw new PasswordErrorException();
		}
		return admin;
		
	}
}
