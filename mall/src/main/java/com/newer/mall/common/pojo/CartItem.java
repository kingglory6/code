package com.newer.mall.common.pojo;

import java.util.Map;

public class CartItem {

	private Commodity commodity;
	private Map<Integer, CartItemParam> param;

	public Commodity getCommodity() {
		return commodity;
	}

	public void setCommodity(Commodity commodity) {
		this.commodity = commodity;
	}

	public Map<Integer, CartItemParam> getParam() {
		return param;
	}

	public void setParam(Map<Integer, CartItemParam> param) {
		this.param = param;
	}

}
