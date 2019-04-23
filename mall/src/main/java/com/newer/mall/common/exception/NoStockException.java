package com.newer.mall.common.exception;
/**
  * 库存不足异常
 * @author LiuLinJie
 *
 */
public class NoStockException extends Exception {
    public NoStockException	(String msg) {
    	super(msg);
    }
}
