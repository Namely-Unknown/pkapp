package com.plantkeeper.dto;

import com.plantkeeper.utils.States;

public class ShippingCoDTO {

	private Long id;
	private Long companyId;
	private String name;
	private States state;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public States getState() {
		return state;
	}
	public void setState(States state) {
		this.state = state;
	}
	
}
