package com.newer.mall.common.pojo;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

public class Orders {

	private int id;
	private Customer customer;
	private String name;
	private String phone;
	private String address;
	private Date time;
	private int payStatus;
	private int payway;
	private int sendStatus;
	private int hidden;
	private BigDecimal total;
	private List<Item> item;
	private String wuliu;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public int getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(int payStatus) {
		this.payStatus = payStatus;
	}

	public int getPayway() {
		return payway;
	}

	public void setPayway(int payway) {
		this.payway = payway;
	}

	public int getSendStatus() {
		return sendStatus;
	}

	public void setSendStatus(int sendStatus) {
		this.sendStatus = sendStatus;
	}

	public int getHidden() {
		return hidden;
	}

	public void setHidden(int hidden) {
		this.hidden = hidden;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public List<Item> getItem() {
		return item;
	}

	public void setItem(List<Item> item) {
		this.item = item;
	}

	public String getWuliu() {
		return wuliu;
	}

	public void setWuliu(String wuliu) {
		this.wuliu = wuliu;
	}

	@Override
	public String toString() {
		return "Orders [id=" + id + ", customer=" + customer + ", name=" + name + ", phone=" + phone + ", address="
				+ address + ", time=" + time + ", payStatus=" + payStatus + ", payway=" + payway + ", sendStatus="
				+ sendStatus + ", hidden=" + hidden + ", total=" + total + ", item=" + item + ", wuliu=" + wuliu + "]";
	}
	
	

}
