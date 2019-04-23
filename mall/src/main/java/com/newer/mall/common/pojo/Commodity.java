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
	private int recommend;
	private int stock;
	private Category category;
	private Brand brand;
	private List<Spec> specList;
	private List<Comment> comment;

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

	public int getShelf() {
		return shelf;
	}

	public void setShelf(int shelf) {
		this.shelf = shelf;
	}

	public int getRecommend() {
		return recommend;
	}

	public void setRecommed(int recommend) {
		this.recommend = recommend;
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

	public List<Comment> getComment() {
		return comment;
	}

	public void setComment(List<Comment> comment) {
		this.comment = comment;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}
	
	

}
