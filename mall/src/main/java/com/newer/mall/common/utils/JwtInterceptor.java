package com.newer.mall.common.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.newer.mall.common.exception.UserLoginException;

import io.jsonwebtoken.Claims;

/**
 * 拦截器
 * @author easy
 *
 */
public class JwtInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
//		if(request.getMethod().equals("OPTIONS")) {
//			System.out.println(1);
//			return true;
//		}
		System.out.println(request.getMethod());
		if(request.getMethod().equals("OPTIONS")) {
			return true;
		}
		String authHeader = request.getHeader("Authorization");
		System.out.println(authHeader);
		if (authHeader == null || !authHeader.startsWith("Bearer")) {
			
			throw new UserLoginException("用户未登录");
		}
		// 取得token
		String token = authHeader.substring(6);
		// 验证token
		Claims claims = JwtUtil.checkToken(token);
		request.setAttribute("username", claims.getSubject());
		return true;
	}
}
