package com.newer.mall.common.utils;

import java.util.Date;

import javax.servlet.ServletException;

import com.newer.mall.common.exception.UserLoginException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtUtil {
	final static String base64EncodedSecretKey = "asdf";
	final static long TOKEN_EXP = 1000 * 60 * 10;// token过期时间

	/**
	 * 加密
	 * 
	 * @param email
	 * @return
	 */
	public static String getToken(String email) {
		return Jwts.builder().setSubject(email).claim("roles", "user").setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + TOKEN_EXP))
				.signWith(SignatureAlgorithm.HS256, base64EncodedSecretKey).compact();

	}

	/**
	 * 解密
	 * 
	 * @param token
	 * @return
	 * @throws ServletException
	 * @throws UserLoginException
	 */
	public static Claims checkToken(String token) throws ServletException, UserLoginException {
		try {
			Claims claims = Jwts.parser().setSigningKey(base64EncodedSecretKey).parseClaimsJws(token).getBody();
			return claims;
		} catch (ExpiredJwtException e1) {
			throw new UserLoginException("登录信息过期，请重新登录");
		} catch (Exception e) {
			throw new UserLoginException("用户未登录，请重新登录");
		}
	}
}
