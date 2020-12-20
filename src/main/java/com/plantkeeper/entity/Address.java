package com.plantkeeper.entity;

import javax.persistence.*;

import com.plantkeeper.utils.States;

@Entity
public class Address {

	@Id
	@GeneratedValue
	private Long id;
	private String street;
	private String street2;
	private String city;
	private States state; //TODO: Create a state ENUM
	private String zip;
	private String name;
	private String isMain;
	
	@ManyToOne
	@JoinColumn(name = "company_id")
	private Company company;
	
	// CONSTRUCTOR
	public Address() {}

	
	// GETTER SETTERS
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getStreet2() {
		return street2;
	}

	public void setStreet2(String street2) {
		this.street2 = street2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public States getState() {
		return state;
	}


	public void setState(States state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIsMain() {
		return isMain;
	}

	public void setIsMain(String isMain) {
		this.isMain = isMain;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

}
