package com.plantkeeper.dto;

import java.time.LocalDate;

import com.plantkeeper.utils.LostProductCode;

public class LostProductDTO {

	private Long productId;
	private LocalDate logDate;
	private int units;
	private LostProductCode code;
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
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
	public LocalDate getLogDate() {
		return logDate;
	}
	public void setLogDate(LocalDate logDate) {
		this.logDate = logDate;
	}
}
