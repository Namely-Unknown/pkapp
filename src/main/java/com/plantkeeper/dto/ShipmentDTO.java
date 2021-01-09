package com.plantkeeper.dto;

import java.time.LocalDate;

import com.plantkeeper.utils.ShipmentStatus;

public class ShipmentDTO {

	private Long id;
	private Long shipperId;
	private ShipmentStatus status;
	private LocalDate ordered;
	private LocalDate received;
	
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
	public ShipmentStatus getStatus() {
		return status;
	}
	public void setStatus(ShipmentStatus status) {
		this.status = status;
	}	
}
