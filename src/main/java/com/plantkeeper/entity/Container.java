package com.plantkeeper.entity;

import java.util.List;

import javax.persistence.*;

@Entity
public class Container {

	@Id
	@GeneratedValue
	private Long id;
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "company_id")
	private Company company;
	
	@OneToMany(mappedBy = "container", cascade = CascadeType.ALL)
	private List<Product> products;
	
	
	// CONSTRUCTOR
	public Container() {}

	
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

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

}
