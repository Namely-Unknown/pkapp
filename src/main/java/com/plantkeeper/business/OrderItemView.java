package com.plantkeeper.business;

import java.math.BigDecimal;

public class OrderItemView {

	private Long id;
	private int units;
	private BigDecimal unitPrice;
	// TODO: Might want returned items in this
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getUnits() {
		return units;
	}
	public void setUnits(int units) {
		this.units = units;
	}
	public BigDecimal getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}
	
	
}
