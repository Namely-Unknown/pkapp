package com.plantkeeper.utils;

public enum ShipmentStatus {

	ORDERED("ordered"),
	RECEIVED("received");
	
	private final String status;
	
	private ShipmentStatus(String s) {
		status = s;
	}
	
	public String toString() {
		return this.status;
	}
}
