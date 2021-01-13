package com.plantkeeper.data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.plantkeeper.entity.OrderItem;

public class DataSetter {

	public static ArrayList<TimeData> setDataList(List<OrderItem> itemList) {
		// Set up the array list and add five holders
		ArrayList<TimeData> returnList = new ArrayList<TimeData>();
		for (int i = 0; i < 5; i++) {
			returnList.add(new TimeData());
		}

		LocalDate today = LocalDate.now();

		// Loop all items and add to data
		for (OrderItem item : itemList) {
			long daySinceOrder = today.toEpochDay() - item.getOrder().getCreated().toEpochDay();

			if (daySinceOrder < 31) {
				returnList.get(0).addUnits(item.getUnits());
				returnList.get(0).addSales(item.getUnitPrice().multiply(BigDecimal.valueOf(item.getUnits())));
			} else if (daySinceOrder < 91) {
				returnList.get(1).addUnits(item.getUnits());
				returnList.get(1).addSales(item.getUnitPrice().multiply(BigDecimal.valueOf(item.getUnits())));
			} else if (daySinceOrder < 181) {
				returnList.get(2).addUnits(item.getUnits());
				returnList.get(2).addSales(item.getUnitPrice().multiply(BigDecimal.valueOf(item.getUnits())));
			} else if (daySinceOrder < 365) {
				returnList.get(3).addUnits(item.getUnits());
				returnList.get(3).addSales(item.getUnitPrice().multiply(BigDecimal.valueOf(item.getUnits())));
			} else {
				returnList.get(4).addUnits(item.getUnits());
				returnList.get(4).addSales(item.getUnitPrice().multiply(BigDecimal.valueOf(item.getUnits())));
			}
		}

		// Roll up the lower ones
		for (

				int i = 1; i < returnList.size(); i++) {
			TimeData currentData = returnList.get(i);
			TimeData lastData = returnList.get(i - 1);
			currentData.addUnits(lastData.getUnits());
			currentData.addSales(lastData.getSales());
		}
		return returnList;
	}

}
