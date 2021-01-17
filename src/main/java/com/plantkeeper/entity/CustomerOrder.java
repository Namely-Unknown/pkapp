package com.plantkeeper.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.*;

import com.plantkeeper.utils.OrderStatus;

@Entity
public class CustomerOrder {

	@Id
	@GeneratedValue
	private Long id;
	private String poNumber;
	private OrderStatus status;
	private LocalDate created;
	private LocalDate paidDate;
	private BigDecimal received;
	private BigDecimal shipping;
	private String note;
	
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Company customer;
	
	@ManyToOne
	@JoinColumn(name = "person_id")
	private Person person;
	
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	private List<OrderItem> items;

	
	// CONSTRUCTOR
	public CustomerOrder() {}
	
	
	// GETTER SETTERS
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public Company getCustomer() {
		return customer;
	}
	public void setCustomer(Company customer) {
		this.customer = customer;
	}
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	public List<OrderItem> getItems() {
		return items;
	}
	public void setItems(List<OrderItem> items) {
		this.items = items;
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
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public BigDecimal getSubtotal() {
		BigDecimal total = new BigDecimal("0.00");
		for (OrderItem item : this.items) {
			total = total.add(item.getUnitPrice().multiply(BigDecimal.valueOf(item.getUnits())));
		}
		return total;
	}
	
}
