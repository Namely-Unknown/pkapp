package com.plantkeeper.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.plantkeeper.utils.LostProductCode;

@Entity
public class LostProduct {

	@Id
	@GeneratedValue
	private Long id;
	private LocalDate logDate;
	private int units;
	private LostProductCode code;
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public LocalDate getLogDate() {
		return logDate;
	}
	public void setLogDate(LocalDate logDate) {
		this.logDate = logDate;
	}
	public int getUnits() {
		return units;
	}
	public void setUnits(int units) {
		this.units = units;
	}
	public LostProductCode getCode() {
		return code;
	}
	public void setCode(LostProductCode code) {
		this.code = code;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	
}
