package com.plantkeeper.sorting;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.plantkeeper.data.MonthlyDetail;
import com.plantkeeper.entity.CustomerOrder;
import com.plantkeeper.entity.OrderItem;

public class TrailingTwelveData {

	
	/**
	 * Uses a provided list of Orders to create an ArrayList of 12 MonthlyDetail objects
	 * @param orderList - The list of orders to build the trailing twelve data
	 * @return An array of ArrayList<MonthlyDetail> with index 0 = currentYear and index 1 = priorYear
	 */
	public static ArrayList<MonthlyDetail>[] setMonthlyDetail(List<CustomerOrder> orderList) {
		
		// CodeReview: Not sure what to do about this warning
		ArrayList<MonthlyDetail> returnArray[] = new ArrayList[2];
		
		LocalDate today = LocalDate.now();
		
		// Reset my current values to fresh, empty array lists
		// MonthlyDetail class (com.plantkeeper.business) is used to store transactions, units sold, revenues, and house a month and sorting index
		ArrayList<MonthlyDetail>currentTrailingTwelveData = new ArrayList<MonthlyDetail>();
		ArrayList<MonthlyDetail>priorTrailingTwelveData = new ArrayList<MonthlyDetail>();

		// Using maps to leverage this collections requirement of unique keys
		// Will put the map data into the arraylist at the end of the method
		// Review Note: Would an Integer as the key be better for any reason?  Could use the LocalDate.getMonth()
		Map<String, MonthlyDetail> ttmMap = new HashMap<>();
		Map<String, MonthlyDetail> ttmMapLastYear = new HashMap<>();

		// Cycle through all customer orders
		for (CustomerOrder order : orderList) {
			
			// If my order was not created before the first day of next month, last year, I want it in my current trailing twelve view
			if (!order.getCreated().isBefore(
					LocalDate.of(today.getYear() - 1, today.getMonthValue() < 12 ? today.getMonthValue() + 1 : 1, 1))) {

				ttmMap = addMonthlyDetailData(ttmMap, order);
				
			// Otherwise, put it in my prior year ttm map
			} else if (!order.getCreated().isBefore(
					LocalDate.of(today.getYear() - 2, today.getMonthValue() < 12 ? today.getMonthValue() + 1 : 1, 1))) {

				ttmMapLastYear = addMonthlyDetailData(ttmMapLastYear, order);
			}
		}

		// Because I need to have a piece of data for months without any sales, I have to cycle through numbers 1 to 12,
		// 	get their equivalent month, and then add a newly initialized MonthlyDetail object if that month isn't already present
		for (int i = 1; i < 13; i++) {
			String month = LocalDate.of(2020, i, 1).getMonth().toString();
			if (!ttmMap.containsKey(month)) {
				ttmMap.put(month, new MonthlyDetail(month, i, today.getMonthValue(), 0, 0, new BigDecimal("0.0")));
			}
			if (!ttmMapLastYear.containsKey(month)) {
				ttmMapLastYear.put(month,
						new MonthlyDetail(month, i, today.getMonthValue(), 0, 0, new BigDecimal("0.0")));
			}
		}

		// Finally, add the details to the ArrayList
		for (MonthlyDetail detail : ttmMap.values()) {
			currentTrailingTwelveData.add(detail);
		}

		for (MonthlyDetail detail : ttmMapLastYear.values()) {
			priorTrailingTwelveData.add(detail);
		}
		
		returnArray[0] = currentTrailingTwelveData;
		returnArray[1] = priorTrailingTwelveData;
		
		return returnArray;

	}
	
	
	/**
	 * 
	 * @param map - The map in which to add the data from the order
	 * @param order - a CustomerOrder object
	 * @return An updated map with the data from the order added as a new key/value or with an updated value on an already present key.
	 * Utilizes the order.getCreated().getMonthValue() as key
	 */
	// Method used to add the MonthlyDetail to a map
	public static Map<String, MonthlyDetail> addMonthlyDetailData(Map<String, MonthlyDetail> map, CustomerOrder order){
		
		LocalDate today = LocalDate.now();
		
		String monthName = order.getCreated().getMonth().toString();
		int monthIndex = order.getCreated().getMonthValue();
		int unitSales = 0;
		BigDecimal revenues = new BigDecimal("0.00");

		// Get those units and revenues
		for (OrderItem item : order.getItems()) {
			unitSales += item.getUnits();
			revenues = revenues.add(BigDecimal.valueOf(item.getUnits()).multiply(item.getUnitPrice()));
		}
		revenues = revenues.add(order.getShipping());

		MonthlyDetail mD;

		// Update or add to map
		if (map.containsKey(monthName)) {
			mD = map.get(monthName);
			mD.setTransactions(mD.getTransactions() + 1);
			mD.setUnits(mD.getUnits() + unitSales);
			mD.setRevenues(mD.getRevenues().add(revenues));
		} else {
			mD = new MonthlyDetail(monthName, monthIndex, today.getMonthValue(), 1, unitSales, revenues);
		}

		map.put(monthName, mD);
		
		return map;
	}
	
	
}
