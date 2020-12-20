package com.plantkeeper.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.plantkeeper.utils.OrderStatus;

public class CustomerOrderDTO {

	private Long id;
	private Long customerId;
	private Long personId;
	private String poNumber;
	private OrderStatus status;
	private LocalDate created;
	private LocalDate paidDate;
	private BigDecimal received;
	private BigDecimal shipping;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public Long getPersonId() {
		return personId;
	}
	public void setPersonId(Long personId) {
		this.personId = personId;
	}
	public LocalDate getCreated() {
		return created;
	}
	public void setCreated(LocalDate created) {
		this.created = created;
	}
	public BigDecimal getReceived() {
		return received;
	}
	public void setReceived(BigDecimal received) {
		this.received = received;
	}
	public String getPoNumber() {
		return poNumber;
	}
	public void setPoNumber(String poNumber) {
		this.poNumber = poNumber;
	}
	public OrderStatus getStatus() {
		return status;
	}
	public void setStatus(OrderStatus status) {
		this.status = status;
	}
	public LocalDate getPaidDate() {
		return paidDate;
	}
	public void setPaidDate(LocalDate paidDate) {
		this.paidDate = paidDate;
	}
	public BigDecimal getShipping() {
		return shipping;
	}
	public void setShipping(BigDecimal shipping) {
		this.shipping = shipping;
	}
	
}
