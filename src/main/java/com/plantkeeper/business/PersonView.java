package com.plantkeeper.business;

import com.plantkeeper.dto.UserSettingsDTO;

public class PersonView {

	private Long id;
	private Long companyId;
	private String first;
	private String last;
	private String title;
	private String email;
	private String phone;
	private boolean isAdmin;
//	private UserSettingsDTO settings;
	
	
	
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
	public String getFirst() {
		return first;
	}
	public void setFirst(String first) {
		this.first = first;
	}
	public String getLast() {
		return last;
	}
	public void setLast(String last) {
		this.last = last;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public boolean isAdmin() {
		return isAdmin;
	}
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
//	public UserSettingsDTO getSettings() {
//		return settings;
//	}
//	public void setSettings(UserSettingsDTO settings) {
//		this.settings = settings;
//	}
}
