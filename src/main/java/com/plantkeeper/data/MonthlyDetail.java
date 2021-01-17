package com.plantkeeper.data;

import java.math.BigDecimal;

public class MonthlyDetail {

	private String name;
	private int monthIndex;
	private int sortIndex; // Used to sort in cases of trailing twelve month
	private int transactions;
	private int units;
	private BigDecimal revenues;
	
	public MonthlyDetail (String name, int monthIndex, int currentMonthIndex, int transactions, int units, BigDecimal revenues) {
		this.name = name;
		this.monthIndex = monthIndex;
		if (monthIndex > currentMonthIndex) {
			this.sortIndex = monthIndex - currentMonthIndex - 1;
		} else {
			this.sortIndex = monthIndex - currentMonthIndex + 11;
		}
		this.transactions = transactions;
		this.units = units;
		this.revenues = revenues;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getMonthIndex() {
		return monthIndex;
	}
	public void setMonthIndex(int monthIndex) {
		this.monthIndex = monthIndex;
	}
	public int getSortIndex() {
		return sortIndex;
	}
	public void setSortIndex(int sortIndex) {
		this.sortIndex = sortIndex;
	}
	public int getTransactions() {
		return transactions;
	}
	public void setTransactions(int transactions) {
		this.transactions = transactions;
	}
	public int getUnits() {
		return units;
	}
	public void setUnits(int units) {
		this.units = units;
	}
	public BigDecimal getRevenues() {
		return revenues;
	}
	public void setRevenues(BigDecimal revenues) {
		this.revenues = revenues;
	}
}

