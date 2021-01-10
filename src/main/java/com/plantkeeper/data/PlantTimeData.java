package com.plantkeeper.data;

import java.math.BigDecimal;

public class PlantTimeData {

	private BigDecimal revenueSold;
	private BigDecimal revenueReturned;
	private int soldCount;
	private int returnCount;
	
	public PlantTimeData() {	
		this.revenueReturned = new BigDecimal("0.00");
		this.revenueSold = new BigDecimal("0.00");
		this.soldCount = 0;
		this.returnCount = 0;
	}
	
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
	public int getSoldCount() {
		return soldCount;
	}
	public void setSoldCount(int soldCount) {
		this.soldCount = soldCount;
	}
	public int getReturnCount() {
		return returnCount;
	}
	public void setReturnCount(int returnCount) {
		this.returnCount = returnCount;
	}	
}
