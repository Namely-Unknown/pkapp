package com.plantkeeper.business;

import java.math.BigDecimal;

public class ShipmentItemView {

	
	private Long id;
	private Long shipmentId;
	private Long productId;
	private ProductView product;
	private BigDecimal unitPrice;
	private int units;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getShipmentId() {
		return shipmentId;
	}
	public void setShipmentId(Long shipmentId) {
		this.shipmentId = shipmentId;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
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
	public ProductView getProduct() {
		return product;
	}
	public void setProduct(ProductView product) {
		this.product = product;
	}
	

}
