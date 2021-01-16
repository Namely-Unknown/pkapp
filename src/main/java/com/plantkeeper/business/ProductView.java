package com.plantkeeper.business;

import java.math.BigDecimal;

public class ProductView {

	private Long id;
	private boolean discontinued;
	private BigDecimal price;
	private int skuInt;
	private int unitsInStock;
	private PlantView plant;
	private ContainerView container;	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public PlantView getPlant() {
		return plant;
	}
	public void setPlant(PlantView plant) {
		this.plant = plant;
	}
	public ContainerView getContainer() {
		return container;
	}
	public void setContainer(ContainerView container) {
		this.container = container;
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
