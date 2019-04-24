package com.newer.mall.common.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

public class Admin {

	private int id;
	
	private String account;
	
	@JsonIgnore
	private String password;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setAdminName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Admin [id=" + id + ", account=" + account + ", password=" + password + ", name=" + name + "]";
	}
	
	

}
