package com.newer.mall.admin.account.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.newer.mall.admin.account.service.AdminService;
import com.newer.mall.common.exception.AccountNotFoundException;
import com.newer.mall.common.exception.PasswordErrorException;
import com.newer.mall.common.pojo.Admin;
import com.newer.mall.common.utils.Password;

@RestController
@RequestMapping("api/v1/admin")
@CrossOrigin
public class AdminAccountController {
	
	@Autowired
	AdminService service;
	
	@PostMapping("/login")
	public Map<String,Object> login(@RequestParam("account") String account,@RequestParam("password") String password,HttpSession session){
		Map<String,Object> map = new HashMap<>();
		Admin admin = null;
		try {
			admin = service.login(account, password);
		} catch (AccountNotFoundException e) {
			map.put("code", "AccountNotFound");
			return map;
		} catch (PasswordErrorException e) {
			map.put("code", "PasswordError");
			return map;
		}
		map.put("code", "ok");
		map.put("account", admin);
		String key = Password.toSHA2(account+password+new Date().toString());
		session.setAttribute("key", key);
		map.put("key", key);
		return map;
	}

}
