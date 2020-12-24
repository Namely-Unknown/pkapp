package com.plantkeeper.business;

public class PlantView {

	private Long id;
	private String name;
	private CategoryView category;
	
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
}
