package com.plantkeeper.business;

import java.util.ArrayList;

import com.plantkeeper.data.TimeData;

public class CategoryDetailView {

	private Long id;
	private String name;
	private String skuPrefix;
	private int plantCount;
	private CustomerOrderView lastOrder;
	private ArrayList<TimeData> data;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSkuPrefix() {
		return skuPrefix;
	}
	public void setSkuPrefix(String skuPrefix) {
		this.skuPrefix = skuPrefix;
	}
	public int getPlantCount() {
		return plantCount;
	}
	public void setPlantCount(int plantCount) {
		this.plantCount = plantCount;
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
