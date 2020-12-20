package com.plantkeeper.utils;

public enum OrderStatus {

	FULFILLED("Fulfilled"),
	RETURNED("Returned"),
	PAID("Paid");

	private final String status;
	
	private OrderStatus(String s) {
		status = s;
	}
	
	public String toString() {
		return this.status;
	}
	
	// ONLY USE FULFILLED, AND PAID/RETURNED
	// ONLY ALLOW EDITING IF IN_PROCESS
	
	
}
