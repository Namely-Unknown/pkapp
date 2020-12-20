package com.plantkeeper.dto;

import java.math.BigDecimal;

public class ProductDTO {

	private Long id;
	private Long plantId;
	private Long containerId;
	private BigDecimal price;
	private int skuInt;
	
	
	
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
	
	
}
