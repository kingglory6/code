package com.newer.mall.admin.account.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.newer.mall.admin.account.service.impl.AdminServiceImpl;
import com.newer.mall.common.exception.AccountNotFoundException;
import com.newer.mall.common.exception.PasswordErrorException;
import com.newer.mall.common.pojo.Admin;

@RestController
@RequestMapping("api/v1/admin")
@CrossOrigin
public class AdminAccountController {
	
	@Autowired
	AdminServiceImpl service;
	
	@PostMapping("/login")
	public Map<String,Object> login(String account,String password){
		Map<String,Object> map = new HashMap<>();
		Admin adminForBase = null;
		try {
			adminForBase = service.login(account, password);
		} catch (AccountNotFoundException e) {
			map.put("code", "AccountNotFound");
			return map;
		} catch (PasswordErrorException e) {
			map.put("code", "PasswordError");
			return map;
		}
		String token = service.getToken(adminForBase);
		map.put("token", token);
		map.put("user", adminForBase);
		return map;
	}
	
	
}
