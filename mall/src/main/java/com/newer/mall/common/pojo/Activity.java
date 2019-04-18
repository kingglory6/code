package com.newer.mall.common.pojo;

import java.math.BigDecimal;
import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Activity {

	private int id;
	
	private int type;
	
	private Commodity commodity;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8") 
	private Date startTime;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8") 
	private Date endTime;
	
	private int stock;
	
	private BigDecimal price;
	
	

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Commodity getCommodity() {
		return commodity;
	}

	public void setCommodity(Commodity commodity) {
		this.commodity = commodity;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Activity [id=" + id + ", type=" + type + ", commodity=" + commodity + ", startTime=" + startTime
				+ ", endTime=" + endTime + ", stock=" + stock + ", price=" + price + "]";
	}
	
	

}
