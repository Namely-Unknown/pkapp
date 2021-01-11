package com.plantkeeper.data;

import java.math.BigDecimal;

public class PlantTimeData {

	private BigDecimal sales;
	private BigDecimal returned;
	private int units;
	private int returnCount;
	
	public PlantTimeData() {	
		this.returned = new BigDecimal("0.00");
		this.sales = new BigDecimal("0.00");
		this.units = 0;
		this.returnCount = 0;
	}
	
	public BigDecimal getSales() {
		return sales;
	}
	public void setSales(BigDecimal sales) {
		this.sales = sales;
	}
	public BigDecimal getReturned() {
		return returned;
	}
	public void setReturned(BigDecimal returned) {
		this.returned = returned;
	}
	public int getUnits() {
		return units;
	}
	public void setUnits(int units) {
		this.units = units;
	}
	public int getReturnCount() {
		return returnCount;
	}
	public void setReturnCount(int returnCount) {
		this.returnCount = returnCount;
	}	
	public void addSales(BigDecimal addSales) {
		this.sales = this.sales.add(addSales);
	}
	public void addReturned(BigDecimal addReturned) {
		this.returned = this.returned.add(addReturned);
	}
	public void addUnits(int addedUnits) {
		this.units += addedUnits;
	}
	public void addReturnCount(int addedReturnCount) {
		this.returnCount += addedReturnCount;
	}
}
