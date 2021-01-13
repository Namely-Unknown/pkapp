package com.plantkeeper.business;

import java.util.ArrayList;

import com.plantkeeper.data.TimeData;

public class ContainerDetailView {

	private Long id;
	private String name;
	private int productCount;
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
	public int getProductCount() {
		return productCount;
	}
	public void setProductCount(int productCount) {
		this.productCount = productCount;
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
