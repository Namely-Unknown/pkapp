package com.plantkeeper.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.*;

@Entity
public class Shipment {

	@Id
	@GeneratedValue
	private Long id;
	private LocalDate ordered;
	private LocalDate received;
	
	@ManyToOne
	@JoinColumn(name = "shipper_id")
	private ShippingCo shipper;
	
	@OneToMany(mappedBy = "shipment", cascade = CascadeType.ALL)
	private List<ShipmentItem> items;
	
	
	// CONSTRUCTOR
	public Shipment() {}


	// GETTER SETTERS
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public LocalDate getOrdered() {
		return ordered;
	}
	public void setOrdered(LocalDate ordered) {
		this.ordered = ordered;
	}
	public LocalDate getReceived() {
		return received;
	}
	public void setReceived(LocalDate received) {
		this.received = received;
	}
	public ShippingCo getShipper() {
		return shipper;
	}
	public void setShipper(ShippingCo shipper) {
		this.shipper = shipper;
	}
	public List<ShipmentItem> getItems() {
		return items;
	}
	public void setItems(List<ShipmentItem> items) {
		this.items = items;
	}
}
