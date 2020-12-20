package com.plantkeeper.sorting;

import java.util.Comparator;

import com.plantkeeper.entity.CustomerOrder;

public class CustomerOrderDateSortDesc implements Comparator<CustomerOrder> {

	@Override // Want to return in descending since that is most recent
	public int compare(CustomerOrder o1, CustomerOrder o2) {
		return o2.getCreated().compareTo(o1.getCreated());
	}

	
	
}
