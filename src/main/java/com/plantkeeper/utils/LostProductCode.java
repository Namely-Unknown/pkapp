package com.plantkeeper.utils;

public enum LostProductCode {

	IP("Insect/Pest"),
	PD("Physical Damage"),
	LP("Lost Product/Unknown");
	
	private final String status;
	
	private LostProductCode(String s) {
		status = s;
	}
	
	public String toString() {
		return this.status;
	}
	
}
