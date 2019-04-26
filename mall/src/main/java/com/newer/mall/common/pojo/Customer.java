package com.newer.mall.common.pojo;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Customer {

	private int id;
	private String name;
	private String phone;
	private String email;
	
	@JsonIgnore
	private String password;
	private List<Address> addressList;
	private Map<Integer, CartItem> cart;
	private List<Collection> collectionList;
	private List<History> historyList;
	
	
	

	public List<History> getHistoryList() {
		return historyList;
	}

	public void setHistoryList(List<History> historyList) {
		this.historyList = historyList;
	}

	public List<Collection> getCollectionList() {
		return collectionList;
	}

	public void setCollectionList(List<Collection> collectionList) {
		this.collectionList = collectionList;
	}

	public Map<Integer, CartItem> getCart() {
		return cart;
	}

	public void setCart(Map<Integer, CartItem> cart) {
		this.cart = cart;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Address> getAddressList() {
		return addressList;
	}

	public void setAddressList(List<Address> addressList) {
		this.addressList = addressList;
	}

}
