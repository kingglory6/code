package com.newer.mall.admin.account.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.newer.mall.admin.account.service.AdminService;
import com.newer.mall.common.exception.AccountNotFound;
import com.newer.mall.common.exception.PasswordError;
import com.newer.mall.common.pojo.Admin;

@RestController
@RequestMapping("api/v1/admin")
public class AdminAccountController {
	
	@Autowired
	AdminService service;
	
	@PostMapping("/login")
	public Map<String,Object> login(@RequestParam("account") String account,@RequestParam("password") String password){
		Map<String,Object> map = new HashMap<>();
		Admin admin = null;
		try {
			admin = service.login(account, password);
		} catch (AccountNotFound e) {
			map.put("code", "AccountNotFound");
			return map;
		} catch (PasswordError e) {
			map.put("code", "PasswordError");
			return map;
		}
		map.put("code", "ok");
		map.put("account", admin);
		return map;
	}

}
