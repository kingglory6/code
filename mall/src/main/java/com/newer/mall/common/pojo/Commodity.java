package com.newer.mall.common.pojo;

import java.math.BigDecimal;
import java.util.List;

public class Commodity {

	private int id;
	private String title;
	private String description;
	private BigDecimal price;
	private BigDecimal discount;
	private int shelf;
	private int recommed;
	private int stock;
	private Category category;
	private Brand brand;
	private List<Spec> specList;

	
	
	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public boolean getShelf() {
		return shelf;
	}

	public void setShelf(boolean shelf) {
		this.shelf = shelf;
	}

	public int getRecommed() {
		return recommed;
	}

	public void setRecommed(int recommed) {
		this.recommed = recommed;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public List<Spec> getSpecList() {
		return specList;
	}

	public void setSpecList(List<Spec> specList) {
		this.specList = specList;
	}

}
