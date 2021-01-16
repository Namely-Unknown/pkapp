package com.plantkeeper.sorting;

import java.util.Comparator;

import com.plantkeeper.entity.OrderItem;


public class OrderItemDateSortDesc implements Comparator<OrderItem> {

	@Override // Want to return in descending since that is most recent
	public int compare(OrderItem o1, OrderItem o2) {
		return o2.getOrder().getCreated().compareTo(o1.getOrder().getCreated());
	}
	
}
