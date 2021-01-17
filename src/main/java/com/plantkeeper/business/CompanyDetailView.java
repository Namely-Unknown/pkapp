package com.plantkeeper.business;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.plantkeeper.data.CompanyDashboardData;
import com.plantkeeper.data.MonthlyDetail;
import com.plantkeeper.dto.AddressDTO;
import com.plantkeeper.entity.Address;
import com.plantkeeper.entity.CustomerOrder;
import com.plantkeeper.sorting.CustomerOrderDateSortDesc;
import com.plantkeeper.sorting.TrailingTwelveData;

public class CompanyDetailView {

	public Long id;
	public String name;
	private BigDecimal ytdRev;
	private BigDecimal ytdRevPrior;
	private List<AddressDTO> addresses;
	private ArrayList<CompanyDashboardData> dashboardData;
	private ArrayList<MonthlyDetail> currentTrailingTwelveData;
	private ArrayList<MonthlyDetail> priorTrailingTwelveData;

	public void setYTDRev(List<CustomerOrder> orderList) {
		LocalDate firstOfYear = LocalDate.of(LocalDate.now().getYear(), 1, 1);
		LocalDate lastYearStart = LocalDate.of(LocalDate.now().getYear() - 1, 1, 1);

		// Initialize
		this.ytdRev = new BigDecimal(0.0);
		this.ytdRevPrior = new BigDecimal(0.0);

		// Sort the orders so we don't have to check every one
		orderList.sort(new CustomerOrderDateSortDesc());

		for (CustomerOrder order : orderList) {

			// If the order is NOT from before the first of the year, add to YTD
			if (!order.getCreated().isBefore(firstOfYear)) {
				this.ytdRev = this.ytdRev.add(order.getSubtotal().add(order.getShipping()));
			}

			// If the order is NOT before lastYearStart and NOT after firstOfYear minus 1
			else if (!order.getCreated().isBefore(lastYearStart)
					&& !order.getCreated().isAfter(firstOfYear.minusDays(1))) {
				this.ytdRevPrior = this.ytdRevPrior.add(order.getSubtotal().add(order.getShipping()));
			}

			// If neither of the above, break the process and don't keep checking order
			// create dates
			else {
				break;
			}
		}
	}

	public void setTrailingTwelveData(List<CustomerOrder> orderList) {

		ArrayList<MonthlyDetail>[] trailingDetail = TrailingTwelveData.setMonthlyDetail(orderList);
		this.currentTrailingTwelveData = trailingDetail[0];
		this.priorTrailingTwelveData = trailingDetail[1];
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
	public ArrayList<CompanyDashboardData> getDashboardData() {
		return dashboardData;
	}
	public void setDashboardData(ArrayList<CompanyDashboardData> dashboardData) {
		this.dashboardData = dashboardData;
	}
	public ArrayList<MonthlyDetail> getCurrentTrailingTwelveData() {
		return currentTrailingTwelveData;
	}
	public void setCurrentTrailingTwelveData(ArrayList<MonthlyDetail> currentTrailingTwelveData) {
		this.currentTrailingTwelveData = currentTrailingTwelveData;
	}
	public ArrayList<MonthlyDetail> getPriorTrailingTwelveData() {
		return priorTrailingTwelveData;
	}
	public void setPriorTrailingTwelveData(ArrayList<MonthlyDetail> priorTrailingTwelveData) {
		this.priorTrailingTwelveData = priorTrailingTwelveData;
	}
	public BigDecimal getYtdRev() {
		return ytdRev;
	}
	public void setYtdRev(BigDecimal ytdRev) {
		this.ytdRev = ytdRev;
	}
	public BigDecimal getYtdRevPrior() {
		return ytdRevPrior;
	}
	public void setYtdRevPrior(BigDecimal ytdRevPrior) {
		this.ytdRevPrior = ytdRevPrior;
	}
	public List<AddressDTO> getAddresses() {
		return addresses;
	}
	public void setAddresses(List<AddressDTO> addresses) {
		this.addresses = addresses;
	}
}
