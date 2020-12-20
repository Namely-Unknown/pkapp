package com.plantkeeper.dto;

public class CompanyDTO {

	private Long id;
	private String name;
	private Long customerOf;
	private String enrollmentKey;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getCustomerOf() {
		return customerOf;
	}
	public void setCustomerOf(Long customerOf) {
		this.customerOf = customerOf;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEnrollmentKey() {
		return enrollmentKey;
	}
	public void setEnrollmentKey(String enrollmentKey) {
		this.enrollmentKey = enrollmentKey;
	}
	
}
