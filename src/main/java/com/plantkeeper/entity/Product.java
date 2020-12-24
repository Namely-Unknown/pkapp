package com.plantkeeper.entity;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.*;

@Entity
public class Product {

	@Id
	@GeneratedValue
	private Long id;
	private BigDecimal price;
	private int skuInt;
	private int unitsInStock;
	private boolean discontinued;
	
	@ManyToOne
	@JoinColumn(name = "plant_id")
	private Plant plant;
	
	@ManyToOne
	@JoinColumn(name = "container_id")
	private Container container;
	
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	private List<OrderItem> orderItems;
	
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	private List<ShipmentItem> receivedItems;
	
	
	// CONSTRUCTOR
	public Product() {}


	// GETTER SETTERS
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public BigDecimal getPrice() {
		return price;
	}


	public void setPrice(BigDecimal price) {
		this.price = price;
	}


	public int getUnitsInStock() {
		return unitsInStock;
	}


	public void setUnitsInStock(int unitsInStock) {
		this.unitsInStock = unitsInStock;
	}


	public int getSkuInt() {
		return skuInt;
	}


	public void setSkuInt(int skuInt) {
		this.skuInt = skuInt;
	}


	public Plant getPlant() {
		return plant;
	}


	public void setPlant(Plant plant) {
		this.plant = plant;
	}


	public Container getContainer() {
		return container;
	}


	public void setContainer(Container container) {
		this.container = container;
	}


	public List<OrderItem> getOrderItems() {
		return orderItems;
	}


	public void setOrderItems(List<OrderItem> items) {
		this.orderItems = items;
	}


	public List<ShipmentItem> getReceivedItems() {
		return receivedItems;
	}


	public void setReceivedItems(List<ShipmentItem> receivedItems) {
		this.receivedItems = receivedItems;
	}


	public boolean isDiscontinued() {
		return discontinued;
	}


	public void setDiscontinued(boolean discontinued) {
		this.discontinued = discontinued;
	}	
	
}
