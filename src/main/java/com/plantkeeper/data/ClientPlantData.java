package com.plantkeeper.data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

public class ClientPlantData {

	private Long id;
	private String name;
	private LocalDate lastPurchase;
	private Long lastPurchaseId;
	private BigDecimal sales;
	private int units;
	private ArrayList<ContainerData> containerData;

	public ClientPlantData(Long id, String name, LocalDate lastPurchase, Long lastPurchaseId, int units, BigDecimal sales) {
		this.id = id;
		this.name = name;
		this.lastPurchase = lastPurchase;
		this.lastPurchaseId = lastPurchaseId;
		this.units = units;
		this.sales = sales;
		this.containerData = new ArrayList<ContainerData>();
	}
	
	public LocalDate getLastPurchase() {
		return lastPurchase;
	}
	public void setLastPurchase(LocalDate lastPurchase) {
		this.lastPurchase = lastPurchase;
	}
	public BigDecimal getSales() {
		return sales;
	}
	public void setSales(BigDecimal sales) {
		this.sales = sales;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getUnits() {
		return units;
	}	
	public void setUnits(int units) {
		this.units = units;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
}
