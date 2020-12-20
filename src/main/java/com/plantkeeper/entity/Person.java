package com.plantkeeper.entity;

import java.util.List;

import javax.persistence.*;

@Entity
public class Person {

	@Id
	@GeneratedValue
	private Long id;
	private String first;
	private String last;
	private String password;
	private String email;
	private String phone;
	private boolean isAdmin;
	
	@ManyToOne
	@JoinColumn(name = "company_id")
	private Company company;
	
	// DO NOT WANT TO CASCADE ALL => If a person is removed, we do NOT want all orders killed in db
	@OneToMany(mappedBy = "person")
	private List<CustomerOrder> orders;
	
	
	// CONSTRUCTOR
	public Person() {}

	
	// GETTER SETTERS
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirst() {
		return first;
	}

	public void setFirst(String first) {
		this.first = first;
	}

	public String getLast() {
		return last;
	}

	public void setLast(String last) {
		this.last = last;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public List<CustomerOrder> getOrders() {
		return orders;
	}

	public void setOrders(List<CustomerOrder> orders) {
		this.orders = orders;
	}
}
