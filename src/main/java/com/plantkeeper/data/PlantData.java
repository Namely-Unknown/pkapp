package com.plantkeeper.data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;


public class PlantData {

	private Long id;
	private String name;
	private BigDecimal sales;
	private int units;
	private LocalDate lastPurchase;
	private Long lastPurchaseId;
	private ArrayList<ContainerData> containerData;
	
	public PlantData() {}
	
	public PlantData(Long id, String name, LocalDate lastPurchase, Long lastPurchaseId, int units, BigDecimal sales) {
		this.id = id;
		this.name = name;
		this.lastPurchase = lastPurchase;
		this.lastPurchaseId = lastPurchaseId;
		this.units = units;
		this.sales = sales;
		this.containerData = new ArrayList<ContainerData>();
	}
	
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
	public BigDecimal getSales() {
		return sales;
	}
	public void setSales(BigDecimal sales) {
		this.sales = sales;
	}
	public int getUnits() {
		return units;
	}
	public void setUnits(int units) {
		this.units = units;
	}
	public LocalDate getLastPurchase() {
		return lastPurchase;
	}
	public void setLastPurchase(LocalDate lastPurchase) {
		this.lastPurchase = lastPurchase;
	}
	public Long getLastPurchaseId() {
		return lastPurchaseId;
	}
	public void setLastPurchaseId(Long lastPurchaseId) {
		this.lastPurchaseId = lastPurchaseId;
	}
	public ArrayList<ContainerData> getContainerData() {
		return containerData;
	}
	public void setContainerData(ArrayList<ContainerData> containerData) {
		this.containerData = containerData;
	}
}
