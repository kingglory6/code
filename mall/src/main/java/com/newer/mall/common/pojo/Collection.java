package com.newer.mall.common.pojo;

import java.math.BigDecimal;
import java.sql.Date;

public class Collection {

	private Commodity commodity;
	private BigDecimal price;
	private Date time;

	public Commodity getCommodity() {
		return commodity;
	}

	public void setCommodity(Commodity commodity) {
		this.commodity = commodity;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

}
