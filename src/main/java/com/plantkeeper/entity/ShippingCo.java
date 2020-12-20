package com.plantkeeper.entity;

import java.util.List;

import javax.persistence.*;

import com.plantkeeper.utils.States;

@Entity
public class ShippingCo {

	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private States state;
	
	@OneToMany(mappedBy = "shipper", cascade = CascadeType.ALL)
	private List<Shipment> shipments;
	
	@ManyToOne
	@JoinColumn(name = "company_id")
	private Company company;
	
	
	// CONSTRUCTOR
	public ShippingCo() {}


	// GETTER SETTERS
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


	public States getState() {
		return state;
	}


	public void setState(States state) {
		this.state = state;
	}


	public List<Shipment> getShipments() {
		return shipments;
	}


	public void setShipments(List<Shipment> shipments) {
		this.shipments = shipments;
	}
}
