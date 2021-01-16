package com.plantkeeper.business;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.plantkeeper.data.ClientTimeData;
import com.plantkeeper.dto.AddressDTO;

public class CustomerDetailView {

	private Long id;
	private String name;
	private BigDecimal shipping;
	private List<AddressDTO> addresses;
	private List<PersonView> people;
	private int orderCount;
	private ArrayList<ClientTimeData> data;
	
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
	public BigDecimal getShipping() {
		return shipping;
	}
	public void setShipping(BigDecimal shipping) {
		this.shipping = shipping;
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
	public ArrayList<ClientTimeData> getData() {
		return data;
	}
	public void setData(ArrayList<ClientTimeData> data) {
		this.data = data;
	}
	public int getOrderCount() {
		return orderCount;
	}
	public void setOrderCount(int orderCount) {
		this.orderCount = orderCount;
	}
}
