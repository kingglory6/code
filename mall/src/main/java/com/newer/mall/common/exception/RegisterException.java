package com.newer.mall.common.exception;

/**
 * 用户名占用异常
 * @author easy
 *
 */
public class RegisterException extends Exception{
	public RegisterException(String msg) {
		super(msg);
	}
}
