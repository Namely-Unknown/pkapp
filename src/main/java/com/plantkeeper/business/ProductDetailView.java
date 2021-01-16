package com.plantkeeper.business;

import java.math.BigDecimal;
import java.util.ArrayList;

import com.plantkeeper.data.TimeData;

public class ProductDetailView {

	private Long id;
	private boolean discontinued;
	private BigDecimal price;
	private int skuInt;
	private int unitsInStock;
	private PlantView plant;
	private ContainerView container;
	private CustomerOrderView lastOrder;
	private ArrayList<TimeData> data;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public boolean isDiscontinued() {
		return discontinued;
	}
	public void setDiscontinued(boolean discontinued) {
		this.discontinued = discontinued;
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
	public CustomerOrderView getLastOrder() {
		return lastOrder;
	}
	public void setLastOrder(CustomerOrderView lastOrder) {
		this.lastOrder = lastOrder;
	}
	public ArrayList<TimeData> getData() {
		return data;
	}
	public void setData(ArrayList<TimeData> data) {
		this.data = data;
	}	
}
