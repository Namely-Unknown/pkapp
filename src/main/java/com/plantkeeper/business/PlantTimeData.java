package com.plantkeeper.business;

import java.math.BigDecimal;

public class PlantTimeData {
	
	private BigDecimal revenueSold;
	private BigDecimal revenueReturned;
	private int unitsSold;
	private int unitsReturned;
	
	
	public BigDecimal getRevenueSold() {
		return revenueSold;
	}
	public void setRevenueSold(BigDecimal revenueSold) {
		this.revenueSold = revenueSold;
	}
	public BigDecimal getRevenueReturned() {
		return revenueReturned;
	}
	public void setRevenueReturned(BigDecimal revenueReturned) {
		this.revenueReturned = revenueReturned;
	}
	public int getUnitsSold() {
		return unitsSold;
	}
	public void setUnitsSold(int unitsSold) {
		this.unitsSold = unitsSold;
	}
	public int getUnitsReturned() {
		return unitsReturned;
	}
	public void setUnitsReturned(int unitsReturned) {
		this.unitsReturned = unitsReturned;
	}
	
}
