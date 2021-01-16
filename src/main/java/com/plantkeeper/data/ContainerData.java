package com.plantkeeper.data;

import java.math.BigDecimal;

public class ContainerData {

	private Long id;
	private String name;
	private BigDecimal sales;
	private int units;
	
	public ContainerData() {}
	
	public ContainerData(Long id, String name, int units, BigDecimal sales) {
		this.id = id;
		this.name = name;
		this.units = units;
		this.sales = sales;
	}
	
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
	public BigDecimal getSales() {
		return sales;
	}
	public void setSales(BigDecimal sales) {
		this.sales = sales;
	}
	public int getUnits() {
		return units;
	}
	public void setUnits(int units) {
		this.units = units;
	}
}
