package com.plantkeeper.data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.plantkeeper.entity.Category;
import com.plantkeeper.entity.Company;
import com.plantkeeper.entity.Container;
import com.plantkeeper.entity.CustomerOrder;
import com.plantkeeper.entity.OrderItem;
import com.plantkeeper.entity.Plant;
import com.plantkeeper.sorting.OrderItemDateSortDesc;

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

	public static ArrayList<CompanyDashboardData> setCompanyDashboardData(List<Company> customerList,
			List<CustomerOrder> orderList) {

		ArrayList<CompanyDashboardData> returnList = new ArrayList<CompanyDashboardData>();

		for (int i = 0; i < 5; i++) {
			returnList.add(new CompanyDashboardData());
		}

		LocalDate today = LocalDate.now();

		ArrayList<Map<String, CustomerData>> customerMap = new ArrayList<Map<String, CustomerData>>();
		ArrayList<Map<String, ContainerData>> containerMap = new ArrayList<Map<String, ContainerData>>();
		ArrayList<Map<String, CategoryData>> categoryMap = new ArrayList<Map<String, CategoryData>>();

		for (int i = 0; i < 5; i++) {
			customerMap.add(new HashMap<>());
		}
		for (int i = 0; i < 5; i++) {
			containerMap.add(new HashMap<>());
		}
		for (int i = 0; i < 5; i++) {
			categoryMap.add(new HashMap<>());
		}

		String containerName;
		String categoryName;

		for (CustomerOrder order : orderList) {
			long daySinceOrder = today.toEpochDay() - order.getCreated().toEpochDay();

			if (daySinceOrder < 31) {
				for (OrderItem item : order.getItems()) {
					containerName = item.getProduct().getContainer().getName();
					categoryName = item.getProduct().getPlant().getCategory().getName();

					if (containerMap.get(0).containsKey(containerName)) {
						ContainerData thisData = containerMap.get(0).get(containerName);
						thisData.setUnits(item.getUnits());
						thisData.setSales(item.getUnitPrice().multiply(BigDecimal.valueOf(item.getUnits())));
					} else {
						Container cont = item.getProduct().getContainer();
						containerMap.get(0).put(containerName, new ContainerData(cont.getId(), cont.getName(),
								item.getUnits(), item.getUnitPrice().multiply(BigDecimal.valueOf(item.getUnits()))));
					}

					if (categoryMap.get(0).containsKey(categoryName)) {
						CategoryData thisData = categoryMap.get(0).get(categoryName);
						thisData.setUnits(item.getUnits());
						thisData.setSales(item.getUnitPrice().multiply(BigDecimal.valueOf(item.getUnits())));
					} else {
						Category cat = item.getProduct().getPlant().getCategory();
						categoryMap.get(0).put(categoryName,
								new CategoryData(cat.getId(), cat.getName(), cat.getSkuPrefix(), item.getUnits(),
										item.getUnitPrice().multiply(BigDecimal.valueOf(item.getUnits()))));
					}

				}
			} else if (daySinceOrder < 91) {
				for (OrderItem item : order.getItems()) {
					containerName = item.getProduct().getContainer().getName();
					categoryName = item.getProduct().getPlant().getCategory().getName();

					if (containerMap.get(1).containsKey(containerName)) {
						ContainerData thisData = containerMap.get(1).get(containerName);
						thisData.setUnits(item.getUnits());
						thisData.setSales(item.getUnitPrice().multiply(BigDecimal.valueOf(item.getUnits())));
					} else {
						Container cont = item.getProduct().getContainer();
						containerMap.get(1).put(containerName, new ContainerData(cont.getId(), cont.getName(),
								item.getUnits(), item.getUnitPrice().multiply(BigDecimal.valueOf(item.getUnits()))));
					}

					if (categoryMap.get(1).containsKey(categoryName)) {
						CategoryData thisData = categoryMap.get(1).get(categoryName);
						thisData.setUnits(item.getUnits());
						thisData.setSales(item.getUnitPrice().multiply(BigDecimal.valueOf(item.getUnits())));
					} else {
						Category cat = item.getProduct().getPlant().getCategory();
						categoryMap.get(1).put(categoryName,
								new CategoryData(cat.getId(), cat.getName(), cat.getSkuPrefix(), item.getUnits(),
										item.getUnitPrice().multiply(BigDecimal.valueOf(item.getUnits()))));
					}

				}
			} else if (daySinceOrder < 181) {
				for (OrderItem item : order.getItems()) {
					containerName = item.getProduct().getContainer().getName();
					categoryName = item.getProduct().getPlant().getCategory().getName();

					if (containerMap.get(2).containsKey(containerName)) {
						ContainerData thisData = containerMap.get(2).get(containerName);
						thisData.setUnits(item.getUnits());
						thisData.setSales(item.getUnitPrice().multiply(BigDecimal.valueOf(item.getUnits())));
					} else {
						Container cont = item.getProduct().getContainer();
						containerMap.get(2).put(containerName, new ContainerData(cont.getId(), cont.getName(),
								item.getUnits(), item.getUnitPrice().multiply(BigDecimal.valueOf(item.getUnits()))));
					}

					if (categoryMap.get(2).containsKey(categoryName)) {
						CategoryData thisData = categoryMap.get(2).get(categoryName);
						thisData.setUnits(item.getUnits());
						thisData.setSales(item.getUnitPrice().multiply(BigDecimal.valueOf(item.getUnits())));
					} else {
						Category cat = item.getProduct().getPlant().getCategory();
						categoryMap.get(2).put(categoryName,
								new CategoryData(cat.getId(), cat.getName(), cat.getSkuPrefix(), item.getUnits(),
										item.getUnitPrice().multiply(BigDecimal.valueOf(item.getUnits()))));
					}

				}
			} else if (daySinceOrder < 365) {
				for (OrderItem item : order.getItems()) {
					containerName = item.getProduct().getContainer().getName();
					categoryName = item.getProduct().getPlant().getCategory().getName();

					if (containerMap.get(3).containsKey(containerName)) {
						ContainerData thisData = containerMap.get(3).get(containerName);
						thisData.setUnits(item.getUnits());
						thisData.setSales(item.getUnitPrice().multiply(BigDecimal.valueOf(item.getUnits())));
					} else {
						Container cont = item.getProduct().getContainer();
						containerMap.get(3).put(containerName, new ContainerData(cont.getId(), cont.getName(),
								item.getUnits(), item.getUnitPrice().multiply(BigDecimal.valueOf(item.getUnits()))));
					}

					if (categoryMap.get(3).containsKey(categoryName)) {
						CategoryData thisData = categoryMap.get(3).get(categoryName);
						thisData.setUnits(item.getUnits());
						thisData.setSales(item.getUnitPrice().multiply(BigDecimal.valueOf(item.getUnits())));
					} else {
						Category cat = item.getProduct().getPlant().getCategory();
						categoryMap.get(3).put(categoryName,
								new CategoryData(cat.getId(), cat.getName(), cat.getSkuPrefix(), item.getUnits(),
										item.getUnitPrice().multiply(BigDecimal.valueOf(item.getUnits()))));
					}

				}
			} else {
				for (OrderItem item : order.getItems()) {
					containerName = item.getProduct().getContainer().getName();
					categoryName = item.getProduct().getPlant().getCategory().getName();

					if (containerMap.get(4).containsKey(containerName)) {
						ContainerData thisData = containerMap.get(4).get(containerName);
						thisData.setUnits(item.getUnits());
						thisData.setSales(item.getUnitPrice().multiply(BigDecimal.valueOf(item.getUnits())));
					} else {
						Container cont = item.getProduct().getContainer();
						containerMap.get(4).put(containerName, new ContainerData(cont.getId(), cont.getName(),
								item.getUnits(), item.getUnitPrice().multiply(BigDecimal.valueOf(item.getUnits()))));
					}

					if (categoryMap.get(4).containsKey(categoryName)) {
						CategoryData thisData = categoryMap.get(4).get(categoryName);
						thisData.setUnits(item.getUnits());
						thisData.setSales(item.getUnitPrice().multiply(BigDecimal.valueOf(item.getUnits())));
					} else {
						Category cat = item.getProduct().getPlant().getCategory();
						categoryMap.get(4).put(categoryName,
								new CategoryData(cat.getId(), cat.getName(), cat.getSkuPrefix(), item.getUnits(),
										item.getUnitPrice().multiply(BigDecimal.valueOf(item.getUnits()))));
					}

				}
			}
		}

		for (Company customer : customerList) {
			for (CustomerOrder order : customer.getOrders()) {
				long daySinceOrder = today.toEpochDay() - order.getCreated().toEpochDay();

				if (daySinceOrder < 31) {
					if (customerMap.get(0).containsKey(customer.getName())) {
						CustomerData thisData = customerMap.get(0).get(customer.getName());
						thisData.setSpent(thisData.getSpent().add(order.getSubtotal()));
					} else {
						customerMap.get(0).put(customer.getName(),
								new CustomerData(customer.getName(), order.getSubtotal()));
					}
				} else if (daySinceOrder < 91) {
					if (customerMap.get(1).containsKey(customer.getName())) {
						CustomerData thisData = customerMap.get(1).get(customer.getName());
						thisData.setSpent(thisData.getSpent().add(order.getSubtotal()));
					} else {
						customerMap.get(1).put(customer.getName(),
								new CustomerData(customer.getName(), order.getSubtotal()));
					}
				} else if (daySinceOrder < 181) {
					if (customerMap.get(2).containsKey(customer.getName())) {
						CustomerData thisData = customerMap.get(2).get(customer.getName());
						thisData.setSpent(thisData.getSpent().add(order.getSubtotal()));
					} else {
						customerMap.get(2).put(customer.getName(),
								new CustomerData(customer.getName(), order.getSubtotal()));
					}
				} else if (daySinceOrder < 365) {
					if (customerMap.get(3).containsKey(customer.getName())) {
						CustomerData thisData = customerMap.get(3).get(customer.getName());
						thisData.setSpent(thisData.getSpent().add(order.getSubtotal()));
					} else {
						customerMap.get(3).put(customer.getName(),
								new CustomerData(customer.getName(), order.getSubtotal()));
					}
				} else {
					if (customerMap.get(4).containsKey(customer.getName())) {
						CustomerData thisData = customerMap.get(4).get(customer.getName());
						thisData.setSpent(thisData.getSpent().add(order.getSubtotal()));
					} else {
						customerMap.get(4).put(customer.getName(),
								new CustomerData(customer.getName(), order.getSubtotal()));
					}
				}

			}
		}

		// Roll up the data
		for (int i = 0; i < customerMap.size() - 1; i++) {
			for (String name : customerMap.get(i).keySet()) {
				if (customerMap.get(i + 1).containsKey(name)) {
					CustomerData addData = customerMap.get(i).get(name);
					CustomerData thisData = customerMap.get(i + 1).get(name);
					thisData.setSpent(thisData.getSpent().add(addData.getSpent()));
				} else {
					customerMap.get(i + 1).put(name, customerMap.get(i).get(name));
				}
			}
		}
		
		for (int i = 0; i < containerMap.size() - 1; i++) {
			for (String name : containerMap.get(i).keySet()) {
				if (containerMap.get(i + 1).containsKey(name)) {
					ContainerData addData = containerMap.get(i).get(name);
					ContainerData thisData = containerMap.get(i + 1).get(name);
					thisData.setUnits(thisData.getUnits() + addData.getUnits());
					thisData.setSales(thisData.getSales().add(addData.getSales()));
				}else {
					containerMap.get(i + 1).put(name, containerMap.get(i).get(name));
				}
			}
		}
		
		for (int i = 0; i < categoryMap.size() - 1; i++) {
			for (String name : categoryMap.get(i).keySet()) {
				if (categoryMap.get(i + 1).containsKey(name)) {
					CategoryData addData = categoryMap.get(i).get(name);
					CategoryData thisData = categoryMap.get(i + 1).get(name);
					thisData.setUnits(thisData.getUnits() + addData.getUnits());
					thisData.setSales(thisData.getSales().add(addData.getSales()));
				}else {
					categoryMap.get(i + 1).put(name, categoryMap.get(i).get(name));
				}
			}
		}

		
		for (int i = 0; i < returnList.size(); i++) {

			for (CustomerData data : customerMap.get(i).values()) {
				ArrayList<CustomerData> newList = returnList.get(i).getCustomerData();
				newList.add(data);
				returnList.get(i).setCustomerData(newList);
			}
			for (ContainerData data : containerMap.get(i).values()) {
				ArrayList<ContainerData> newList = returnList.get(i).getContainerData();
				newList.add(data);
				returnList.get(i).setContainerData(newList);
			}
			for (CategoryData data : categoryMap.get(i).values()) {
				ArrayList<CategoryData> newList = returnList.get(i).getCategoryData();
				newList.add(data);
				returnList.get(i).setCategoryData(newList);
			}
			
		}

		return returnList;
	}

	public static ArrayList<ClientTimeData> setCustomerDataList(List<OrderItem> itemList) {
		// Set up the array list and add five holders
		ArrayList<ClientTimeData> returnList = new ArrayList<ClientTimeData>();
		for (int i = 0; i < 5; i++) {
			returnList.add(new ClientTimeData());
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
		for (int i = 1; i < returnList.size(); i++) {
			ClientTimeData currentData = returnList.get(i);
			ClientTimeData lastData = returnList.get(i - 1);
			currentData.addUnits(lastData.getUnits());
			currentData.addSales(lastData.getSales());
		}

		returnList.get(0).setPlantData(setPlantData(itemList, 31));
		returnList.get(1).setPlantData(setPlantData(itemList, 91));
		returnList.get(2).setPlantData(setPlantData(itemList, 181));
		returnList.get(3).setPlantData(setPlantData(itemList, 365));
		returnList.get(4).setPlantData(setPlantData(itemList, 0));
		// TODO: Sort the plant list

		return returnList;

	}

	public static ArrayList<PlantData> setPlantData(List<OrderItem> itemList, int days) {
		ArrayList<PlantData> plantData = new ArrayList<PlantData>();
		LocalDate lookback = LocalDate.now().minusDays(days);

		Map<Long, PlantData> plantMap = new HashMap<>();
		Map<Long, Map<Long, ContainerData>> pcMap = new HashMap<>();

		itemList.sort(new OrderItemDateSortDesc());

		// Seed the map with all purchased plants
		for (OrderItem item : itemList) {
			if (!item.getOrder().getCreated().isBefore(lookback) || days == 0) {
				Map[] mapArray = putPlantToMap(plantMap, pcMap, item);
				plantMap = mapArray[0];
				pcMap = mapArray[1];
			} else {
				break;
			}
		}

		for (PlantData data : plantMap.values()) {
			// Map all the container HashMap into the data.getContainerData.add()
			for (ContainerData cd : pcMap.get(data.getId()).values()) {
				data.getContainerData().add(cd);
			}
			plantData.add(data);
		}

		return plantData;
	}

	public static Map[] putPlantToMap(Map<Long, PlantData> plantMap, Map<Long, Map<Long, ContainerData>> pcMap,
			OrderItem item) {

		/*
		 * CodeReview: I did not parameterize this Map reference because it will hold a
		 * map and a nested map. Do I need to adjust this at all?
		 */
		Map[] mapArray = new Map[2];

		Plant plant = item.getProduct().getPlant();
		Long plantId = plant.getId();
		String name = plant.getName();
		LocalDate purchase = item.getOrder().getCreated();
		int units = item.getUnits();
		BigDecimal sales = item.getUnitPrice().multiply(BigDecimal.valueOf(units));

		PlantData p;

		if (plantMap.containsKey(plantId)) {
			p = plantMap.get(plantId);
			p.setSales(p.getSales().add(sales));
			p.setUnits(p.getUnits() + units);
			if (purchase.isAfter(p.getLastPurchase())) {
				p.setLastPurchase(purchase);
				p.setLastPurchaseId(item.getOrder().getId());
			}

		} else {
			p = new PlantData(plantId, name, purchase, item.getOrder().getId(), units, sales);
		}

		// if the plantId is already in the cMap I need to check if the containerId is
		// in the innerMap
		Map<Long, ContainerData> containerMap;
		if (pcMap.containsKey(plantId)) {
			containerMap = pcMap.get(plantId);
			// Already have the plant registered and need to pull its containerMap
		} else {
			containerMap = new HashMap<>();
			// Do not have the plant registered and need to create a containerMap
		}

		containerMap = putContainerToMap(containerMap, item);

		// Put the containerMap into the pcMap
		pcMap.put(plantId, containerMap);

		// Put the plant data into the plantmap
		plantMap.put(plantId, p);

		mapArray[0] = plantMap;
		mapArray[1] = pcMap;

		return mapArray;
	}

	public static Map<Long, ContainerData> putContainerToMap(Map<Long, ContainerData> map, OrderItem item) {

		Long containerId = item.getProduct().getContainer().getId();
		String containerName = item.getProduct().getContainer().getName();
		int units = item.getUnits();
		BigDecimal price = item.getUnitPrice();
		BigDecimal sales = price.multiply(BigDecimal.valueOf(units));

		ContainerData c;
		if (map.containsKey(containerId)) {
			c = map.get(containerId);
			c.setUnits(c.getUnits() + units);
			c.setSales(c.getSales().add(sales));
		} else {
			c = new ContainerData(containerId, containerName, units, sales);
		}
		map.put(containerId, c);

		return map;
	}

//	public static void setMapData(Map<String, ArrayList<OrderItemView>> plantMap, String name, OrderItemView view) {
//
//		if (plantMap.containsKey(name)) {
//			plantMap.get(name).add(view);
//		} else {
//			plantMap.put(name, new ArrayList<OrderItemView>());
//			System.out.println(plantMap.get(name).size());
//			plantMap.get(name).add(view);
//		}
//	}

}
