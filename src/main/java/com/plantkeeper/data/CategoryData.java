package com.plantkeeper.data;

import java.math.BigDecimal;

public class CategoryData {

	private Long id;
	private String name;
	private String prefix;
	private int units;
	private BigDecimal sales;
	
	public CategoryData(Long id, String name, String prefix, int units, BigDecimal sales) {
		this.id = id;
		this.name = name;
		this.prefix = prefix;
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
	public String getPrefix() {
		return prefix;
	}
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	public int getUnits() {
		return units;
	}
	public void setUnits(int units) {
		this.units = units;
	}
	public BigDecimal getSales() {
		return sales;
	}
	public void setSales(BigDecimal sales) {
		this.sales = sales;
	}
		
}
