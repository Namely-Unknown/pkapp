package com.plantkeeper.dto;

public class CategoryDTO {

	private Long id;
	private Long companyId;
	private String name;
	private String skuPrefix;
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSkuPrefix() {
		return skuPrefix;
	}
	public void setSkuPrefix(String skuPrefix) {
		this.skuPrefix = skuPrefix;
	}
}
