package com.plantkeeper.data;

import java.math.BigDecimal;

public class CustomerData {

	private String name;
	private BigDecimal spent;
	
	public CustomerData() {}
	
	public CustomerData(String name, BigDecimal spent) {
		this.name = name;
		this.spent = spent;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getSpent() {
		return spent;
	}
	public void setSpent(BigDecimal spent) {
		this.spent = spent;
	}
}
