package com.plantkeeper.data;

import java.util.ArrayList;

public class CompanyDashboardData {

	private ArrayList<CategoryData> categoryData;
	private ArrayList<ContainerData> containerData;
	private ArrayList<CustomerData> customerData;
	
	CompanyDashboardData(){
		this.categoryData = new ArrayList<CategoryData>();
		this.containerData = new ArrayList<ContainerData>();
		this.customerData = new ArrayList<CustomerData>();
	}
	
	public ArrayList<CategoryData> getCategoryData() {
		return categoryData;
	}
	public void setCategoryData(ArrayList<CategoryData> categoryData) {
		this.categoryData = categoryData;
	}
	public ArrayList<ContainerData> getContainerData() {
		return containerData;
	}
	public void setContainerData(ArrayList<ContainerData> containerData) {
		this.containerData = containerData;
	}
	public ArrayList<CustomerData> getCustomerData() {
		return customerData;
	}
	public void setCustomerData(ArrayList<CustomerData> customerData) {
		this.customerData = customerData;
	}
}
