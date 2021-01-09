package com.plantkeeper.dto;

public class UserSettingsDTO {

	private Long id;
	private int redProductWarning;
	private int yellowProductWarning;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getRedProductWarning() {
		return redProductWarning;
	}
	public void setRedProductWarning(int redProductWarning) {
		this.redProductWarning = redProductWarning;
	}
	public int getYellowProductWarning() {
		return yellowProductWarning;
	}
	public void setYellowProductWarning(int yellowProductWarning) {
		this.yellowProductWarning = yellowProductWarning;
	}
	
	
}
