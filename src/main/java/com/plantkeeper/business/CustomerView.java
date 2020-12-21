package com.plantkeeper.business;

import java.util.List;

import com.plantkeeper.dto.AddressDTO;

public class CustomerView {

	private Long id;
	private String name;
	private List<AddressDTO> addresses;
	
	
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
	public List<AddressDTO> getAddresses() {
		return addresses;
	}
	public void setAddresses(List<AddressDTO> addresses) {
		this.addresses = addresses;
	}
}
