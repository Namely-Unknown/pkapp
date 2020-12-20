package com.plantkeeper.entity;

import java.util.List;

import javax.persistence.*;

@Entity
public class Plant {

	@Id
	@GeneratedValue
	private Long id;
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;
	
	@OneToMany(mappedBy = "plant", cascade = CascadeType.ALL)
	private List<Product> products;
	
	
	// CONSTRUCTOR
	public Plant(){}

	
	// GETTER SETTERS
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
}
