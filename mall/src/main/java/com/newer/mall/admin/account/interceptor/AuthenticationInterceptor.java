package com.newer.mall.admin.account.interceptor;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

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
import com.newer.mall.common.pojo.Admin;

public class AuthenticationInterceptor implements HandlerInterceptor {

	@Autowired
	AdminService service;

	@Override
	public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Object object) throws NotLoginException, AccountNotFoundException, IOException {
		String token = httpServletRequest.getHeader("token");
		if (!(object instanceof HandlerMethod)) {
			return true;
		}

		HandlerMethod handlerMethod = (HandlerMethod) object;
		Method method = handlerMethod.getMethod();

		if (method.isAnnotationPresent(PassToken.class)) {
			PassToken passToken = method.getAnnotation(PassToken.class);
			if (passToken.required()) {
				return true;
			}
		}

		if (method.isAnnotationPresent(UserLoginToken.class)) {
			UserLoginToken userLoginToken = method.getAnnotation(UserLoginToken.class);
			if (userLoginToken.required()) {
				if (token == null) {
					httpServletResponse.getWriter().write("error,Not Login");
					return false;
				}

				String account = null;
				try {
					account = JWT.decode(token).getAudience().get(0);
				} catch (JWTDecodeException j) {
					httpServletResponse.getWriter().write("error,Token Exception");
					return false;
				}
				
				Admin admin = service.findAdmin(account);
				if (admin == null) {
					httpServletResponse.getWriter().write("error,AccountNotFound");
					return false;
				}

				JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(admin.getPassword())).build();
				try {
					jwtVerifier.verify(token);
				} catch (JWTVerificationException e) {
					httpServletResponse.getWriter().write("error,Token Exception");
					return false;
				}
				return true;
			}
		}

		return true;
	}

}
