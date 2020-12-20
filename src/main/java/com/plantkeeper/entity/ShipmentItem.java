package com.plantkeeper.entity;

import java.math.BigDecimal;

import javax.persistence.*;

@Entity
public class ShipmentItem {

	@Id
	@GeneratedValue
	private Long id;
	private BigDecimal unitPrice;
	private int units;
	
	@ManyToOne
	@JoinColumn(name = "shipment_id")
	private Shipment shipment;
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;
	
	
	// CONSTRUCTOR
	public ShipmentItem() {}

	
	// GETTER SETTERS
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public int getUnits() {
		return units;
	}

	public void setUnits(int units) {
		this.units = units;
	}

	public Shipment getShipment() {
		return shipment;
	}

	public void setShipment(Shipment shipment) {
		this.shipment = shipment;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
}
