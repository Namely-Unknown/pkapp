package com.plantkeeper.business;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.plantkeeper.utils.ShipmentStatus;

public class ShipmentView {

	private Long id;
	private LocalDate ordered;
	private ShipmentStatus status;
	private LocalDate received;
	private Long shipperId;
	private ShippingCoView shipper;
	private BigDecimal cost;
	private int units;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getShipperId() {
		return shipperId;
	}
	public void setShipperId(Long shipperId) {
		this.shipperId = shipperId;
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
	public ShippingCoView getShipper() {
		return shipper;
	}
	public void setShipper(ShippingCoView shipper) {
		this.shipper = shipper;
	}
	public BigDecimal getCost() {
		return cost;
	}
	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}
	public int getUnits() {
		return units;
	}
	public void setUnits(int units) {
		this.units = units;
	}
	public ShipmentStatus getStatus() {
		return status;
	}
	public void setStatus(ShipmentStatus status) {
		this.status = status;
	}
}
