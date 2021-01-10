package com.plantkeeper.business;

import java.util.ArrayList;

import com.plantkeeper.data.PlantTimeData;

// IF HAD THOUGHT EARLIER, WOULD HAVE MADE A "PLANTBUSINESS" CLASS TO HOUSE AN ABSTRACT VERSION?
public class PlantDetailView {

	private Long id;
	private String name;
	private CategoryView category;
	private long productCount;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public CategoryView getCategory() {
		return category;
	}
	public void setCategory(CategoryView category) {
		this.category = category;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getProductCount() {
		return productCount;
	}
	public void setProductCount(long productCount) {
		this.productCount = productCount;
	}
}
