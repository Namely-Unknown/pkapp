package com.plantkeeper.entity;

import java.util.List;

import javax.persistence.*;

@Entity
public class Category {
	
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private String skuPrefix;
	
	@ManyToOne
	@JoinColumn(name = "company_id")
	private Company company;
	
	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
	private List<Plant> plants;
	
	
	// CONSTRUCTOR
	public Category() {}

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

	public String getSkuPrefix() {
		return skuPrefix;
	}

	public void setSkuPrefix(String skuPrefix) {
		this.skuPrefix = skuPrefix;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public List<Plant> getPlants() {
		return plants;
	}

	public void setPlants(List<Plant> plants) {
		this.plants = plants;
	}
	
}
