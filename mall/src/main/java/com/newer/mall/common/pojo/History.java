package com.newer.mall.common.pojo;

import java.sql.Date;

public class History {

	private Commodity commodity;
	private Date time;

	public Commodity getCommodity() {
		return commodity;
	}

	public void setCommodity(Commodity commodity) {
		this.commodity = commodity;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

}
