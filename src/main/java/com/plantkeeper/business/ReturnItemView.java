package com.plantkeeper.business;

import java.time.LocalDate;

public class ReturnItemView {

	private Long id;
	private LocalDate created;
	private OrderItemView orderItem;
	private int units;
	private boolean fundsToAccount;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public LocalDate getCreated() {
		return created;
	}
	public void setCreated(LocalDate created) {
		this.created = created;
	}
	public int getUnits() {
		return units;
	}
	public void setUnits(int units) {
		this.units = units;
	}
	public OrderItemView getOrderItem() {
		return orderItem;
	}
	public void setOrderItem(OrderItemView orderItem) {
		this.orderItem = orderItem;
	}
	public boolean isFundsToAccount() {
		return fundsToAccount;
	}
	public void setFundsToAccount(boolean fundsToAccount) {
		this.fundsToAccount = fundsToAccount;
	}
}
