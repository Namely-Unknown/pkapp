package com.plantkeeper.entity;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.*;

@Entity
public class OrderItem {

	@Id
	@GeneratedValue
	private Long id;
	private int units;
	private BigDecimal unitPrice;
	
	@ManyToOne
	@JoinColumn(name = "order_id")
	private CustomerOrder order;
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;
	
	@OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
	private List<ReturnItem> returns;
	
	
	// CONSTRUCTOR
	public OrderItem() {}


	// GETTER SETTERS
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getUnits() {
		return units;
	}

	public void setUnits(int units) {
		this.units = units;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public CustomerOrder getOrder() {
		return order;
	}

	public void setOrder(CustomerOrder order) {
		this.order = order;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public List<ReturnItem> getReturns() {
		return returns;
	}

	public void setReturns(List<ReturnItem> returns) {
		this.returns = returns;
	}
	
}
