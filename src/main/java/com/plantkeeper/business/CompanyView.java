package com.plantkeeper.business;

import java.math.BigDecimal;
import java.util.List;

import com.plantkeeper.dto.AddressDTO;

public class CompanyView {

	public Long id;
	public String name;
	public BigDecimal shipping;
	private List<PersonView> people;
	private List<AddressDTO> addresses;
	
	public CompanyView() {}

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
	public List<PersonView> getPeople() {
		return people;
	}
	public void setPeople(List<PersonView> people) {
		this.people = people;
	}
	public BigDecimal getShipping() {
		return shipping;
	}
	public void setShipping(BigDecimal shipping) {
		this.shipping = shipping;
	}
}
