package com.plantkeeper.entity;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.*;

@Entity
public class Company {

	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private BigDecimal shipping;
	private Long customerOf;
	private String enrollmentKey;
	private BigDecimal fundsOnAccount;
	
	@OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
	private List<Address> addresses;
	
	@OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
	private List<Person> people;
	
	@OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
	private List<ShippingCo> shippingCompanies;
	
	// LISTS FOR Non-Customers ONLY
	@OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
	private List<Category> categories;
	
	@OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
	private List<Container> containers;
	
	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
	private List<CustomerOrder> orders;
	
	
	// CONSTRUCTOR
	public Company() {}
	
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

	public BigDecimal getShipping() {
		return shipping;
	}

	public void setShipping(BigDecimal shipping) {
		this.shipping = shipping;
	}

	public Long getCustomerOf() {
		return customerOf;
	}

	public void setCustomerOf(Long customerOf) {
		this.customerOf = customerOf;
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public List<Person> getPeople() {
		return people;
	}

	public void setPeople(List<Person> people) {
		this.people = people;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public List<Container> getContainers() {
		return containers;
	}

	public void setContainers(List<Container> containers) {
		this.containers = containers;
	}

	public List<CustomerOrder> getOrders() {
		return orders;
	}

	public void setOrders(List<CustomerOrder> orders) {
		this.orders = orders;
	}

	public List<ShippingCo> getShippingCompanies() {
		return shippingCompanies;
	}

	public void setShippingCompanies(List<ShippingCo> shippingCompanies) {
		this.shippingCompanies = shippingCompanies;
	}

	public String getEnrollmentKey() {
		return enrollmentKey;
	}
	
	public void setEnrollmentKey(String enrollmentKey) {
		this.enrollmentKey = enrollmentKey;
	}

	public BigDecimal getFundsOnAccount() {
		return fundsOnAccount;
	}

	public void setFundsOnAccount(BigDecimal fundsOnAccount) {
		this.fundsOnAccount = fundsOnAccount;
	}
}
