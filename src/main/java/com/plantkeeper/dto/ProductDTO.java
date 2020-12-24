package com.plantkeeper.dto;

import java.math.BigDecimal;

public class ProductDTO {

	private Long id;
	private Long plantId;
	private Long containerId;
	private BigDecimal price;
	private int skuInt;
	private int unitsInStock;
	private boolean discontinued;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getPlantId() {
		return plantId;
	}
	public void setPlantId(Long plantId) {
		this.plantId = plantId;
	}
	public Long getContainerId() {
		return containerId;
	}
	public void setContainerId(Long containerId) {
		this.containerId = containerId;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public int getSkuInt() {
		return skuInt;
	}
	public void setSkuInt(int skuInt) {
		this.skuInt = skuInt;
	}
	public int getUnitsInStock() {
		return unitsInStock;
	}
	public void setUnitsInStock(int unitsInStock) {
		this.unitsInStock = unitsInStock;
	}
	public boolean isDiscontinued() {
		return discontinued;
	}
	public void setDiscontinued(boolean discontinued) {
		this.discontinued = discontinued;
	}
	
	
}
