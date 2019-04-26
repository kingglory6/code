package com.newer.mall.admin.account.interceptor;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.newer.mall.admin.account.service.AdminService;
import com.newer.mall.common.Annotation.PassToken;
import com.newer.mall.common.Annotation.UserLoginToken;
import com.newer.mall.common.exception.AccountNotFoundException;
import com.newer.mall.common.exception.NotLoginException;
import com.newer.mall.common.mapper.AdminLoginMapper;
import com.newer.mall.common.pojo.Admin;

public class AuthenticationInterceptor implements HandlerInterceptor {

	@Autowired
	AdminService service;
	
	@Autowired
	AdminLoginMapper mapper;
	

	@Override
	public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Object object) throws NotLoginException, AccountNotFoundException, IOException {
		Map<String,Object> map = new HashMap<>();
		String token = httpServletRequest.getHeader("token");
		if (!(object instanceof HandlerMethod)) {
			return true;
		}
		
		HandlerMethod handlerMethod = (HandlerMethod) object;
		Method method = handlerMethod.getMethod();

		if (method.isAnnotationPresent(PassToken.class)) {
			PassToken passToken = method.getAnnotation(PassToken.class);
			if (passToken.required()) {
				String account = (String) httpServletRequest.getAttribute("account");
				if(account == null) {
					account = "无";
				}
				mapper.insertLog(account,httpServletRequest.getRemoteAddr(), method.getName(), true,null);	
				return true;
			}
		}
		
		Class<?> c = handlerMethod.getBean().getClass();
		
		//method.isAnnotationPresent(UserLoginToken.class)
		if (c.isAnnotationPresent(UserLoginToken.class)) {
			UserLoginToken userLoginToken = c.getAnnotation(UserLoginToken.class);
			if (userLoginToken.required()) {
				if (token == null) {
					map.put("code", "Not Login");
					httpServletResponse.getWriter().write(JSON.toJSONString(map));
					mapper.insertLog("未登录",httpServletRequest.getRemoteAddr(), method.getName(), false,"未登录");
					return false;
				}

				String account = null;
				try {
					account = JWT.decode(token).getAudience().get(0);
				} catch (JWTDecodeException j) {
					map.put("code", "Token Exception");
					httpServletResponse.getWriter().write(JSON.toJSONString(map));
					mapper.insertLog(account,httpServletRequest.getRemoteAddr(), method.getName(), false,"Token异常");
					return false;
				}
				
				Admin admin = service.findAdmin(account);
				if (admin == null) {
					//map.put("code", "账号不存在");
					//httpServletResponse.getWriter().write(JSON.toJSONString(map));
					mapper.insertLog(account,httpServletRequest.getRemoteAddr(), method.getName(), false,"账号不存在");
					return false;
				}

				JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(admin.getPassword())).build();
				try {
					jwtVerifier.verify(token);
				} catch (JWTVerificationException e) {
					//map.put("code", "密码错误");
					//httpServletResponse.getWriter().write(JSON.toJSONString(map));
					mapper.insertLog(account,httpServletRequest.getRemoteAddr(), method.getName(), false,"PasswordError");
					return false;
				}
				mapper.insertLog(account,httpServletRequest.getRemoteAddr(), method.getName(), true,null);
				return true;
			}
		}
		mapper.insertLog("未知",httpServletRequest.getRemoteAddr(), method.getName(), true,null);
		return true;
	}

}
