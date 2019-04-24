package com.newer.mall.common.pojo;


public class CartItem {

	private Commodity commodity;
	private Spec spec;
	private int quantity;
//	private Map<Integer, CartItemParam> param;
    
	
	
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Spec getSpec() {
		return spec;
	}

	public void setSpec(Spec spec) {
		this.spec = spec;
	}

	public Commodity getCommodity() {
		return commodity;
	}

	public void setCommodity(Commodity commodity) {
		this.commodity = commodity;
	}



}
