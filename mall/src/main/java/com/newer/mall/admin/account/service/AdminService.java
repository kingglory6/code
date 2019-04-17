package com.newer.mall.admin.account.service;

import com.newer.mall.common.exception.AccountNotFoundException;
import com.newer.mall.common.exception.PasswordErrorException;
import com.newer.mall.common.pojo.Admin;

public interface AdminService {
	
	public Admin login(String account,String password) throws AccountNotFoundException, PasswordErrorException;

}
