package com.plantkeeper.business;

import java.util.ArrayList;

import com.plantkeeper.data.TimeData;

// IF HAD THOUGHT EARLIER, WOULD HAVE MADE A "PLANTBUSINESS" CLASS TO HOUSE AN ABSTRACT VERSION?
public class PlantDetailView {

	private Long id;
	private String name;
	private CategoryView category;
	private long productCount;
	private CustomerOrderView lastOrder;
	private ArrayList<TimeData> data;

//	public void setDataList(List<OrderItem> itemList) {
//
//		// Set up the array list and add five holders
//		this.data = new ArrayList<TimeData>();
//		for (int i = 0; i < 5; i++) {
//			this.data.add(new TimeData());
//		}
//
//		LocalDate today = LocalDate.now();
//
//		// Loop all items and add to data
//		for (OrderItem item : itemList) {
//			long daySinceOrder = today.toEpochDay() - item.getOrder().getCreated().toEpochDay();
//
//			if (daySinceOrder < 31) {
//				data.get(0).addUnits(item.getUnits());
//				data.get(0).addSales(item.getUnitPrice().multiply(BigDecimal.valueOf(item.getUnits())));
//			} else if (daySinceOrder < 91) {
//				data.get(1).addUnits(item.getUnits());
//				data.get(1).addSales(item.getUnitPrice().multiply(BigDecimal.valueOf(item.getUnits())));
//			} else if (daySinceOrder < 181) {
//				data.get(2).addUnits(item.getUnits());
//				data.get(2).addSales(item.getUnitPrice().multiply(BigDecimal.valueOf(item.getUnits())));
//			} else if (daySinceOrder < 365) {
//				data.get(3).addUnits(item.getUnits());
//				data.get(3).addSales(item.getUnitPrice().multiply(BigDecimal.valueOf(item.getUnits())));
//			} else {
//				data.get(4).addUnits(item.getUnits());
//				data.get(4).addSales(item.getUnitPrice().multiply(BigDecimal.valueOf(item.getUnits())));
//			}
//		}
//
//		// Roll up the lower ones
//		for (
//
//				int i = 1; i < this.data.size(); i++) {
//			TimeData currentData = this.data.get(i);
//			TimeData lastData = this.data.get(i - 1);
//			currentData.addUnits(lastData.getUnits());
//			currentData.addSales(lastData.getSales());
//		}
//	}

	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public CategoryView getCategory() {
		return category;
	}
	public void setCategory(CategoryView category) {
		this.category = category;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getProductCount() {
		return productCount;
	}
	public void setProductCount(long productCount) {
		this.productCount = productCount;
	}
	public ArrayList<TimeData> getData() {
		return data;
	}
	public void setData(ArrayList<TimeData> data) {
		this.data = data;
	}
	public CustomerOrderView getLastOrder() {
		return lastOrder;
	}
	public void setLastOrder(CustomerOrderView lastOrder) {
		this.lastOrder = lastOrder;
	}
}
