package com.plantkeeper.entity;

import java.time.LocalDate;

import javax.persistence.*;

@Entity
public class ReturnItem {

	@Id
	@GeneratedValue
	private Long id;
	private LocalDate created;
	private int units;
	
	@ManyToOne
	@JoinColumn(name = "item_id")
	private OrderItem item;
	
	
	// CONSTRUCTOR
	public ReturnItem() {}


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


	public int getUnits() {
		return units;
	}


	public void setUnits(int units) {
		this.units = units;
	}


	public OrderItem getItem() {
		return item;
	}


	public void setItem(OrderItem item) {
		this.item = item;
	}
}
