package com.plantkeeper.dto;

import java.math.BigDecimal;

public class CompanyDTO {

	private Long id;
	private String name;
	private BigDecimal shipping;
	private Long customerOf;
	private String enrollmentKey;
	private BigDecimal fundsOnAccount;
	
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
	public BigDecimal getFundsOnAccount() {
		return fundsOnAccount;
	}
	public void setFundsOnAccount(BigDecimal fundsOnAccount) {
		this.fundsOnAccount = fundsOnAccount;
	}
	
}
