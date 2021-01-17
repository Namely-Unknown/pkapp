package com.plantkeeper.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.plantkeeper.entity.Company;
import com.plantkeeper.entity.CustomerOrder;
import com.plantkeeper.entity.Product;
import com.plantkeeper.entity.Shipment;
import com.plantkeeper.entity.ShippingCo;
import com.plantkeeper.exception.CustomerNotFoundException;
import com.plantkeeper.export.AvailabilityReportExcelExporter;
import com.plantkeeper.export.ClientExcelExporter;
import com.plantkeeper.export.ExcelExporter;
import com.plantkeeper.export.InboundReportExcelExporter;
import com.plantkeeper.service.CompanyService;
import com.plantkeeper.service.ProductService;
import com.plantkeeper.service.ShipmentService;
import com.plantkeeper.utils.ShipmentStatus;

@RestController
public class ReportController {

	@Autowired
	private CompanyService service;
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("/api/customerData={customerId}")
	public void customerData(HttpServletResponse response, @PathVariable Long customerId) throws IOException {

		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());
		
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=customerData_" + currentDateTime + ".xlsx";
		response.setHeader(headerKey,  headerValue);
		
		Company customer = service.mapToEntity(service.findById(customerId).orElseThrow(()-> new CustomerNotFoundException(customerId)));
		
		List<CustomerOrder> orderList = customer.getOrders();
		
		ClientExcelExporter exporter = new ClientExcelExporter(orderList);
		
		exporter.export(response);
	}
	
	@GetMapping("/api/inboundreport/{id}")
	public void inboundReport(HttpServletResponse response, @PathVariable Long id) throws IOException{
		
		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());
		
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=customerData_" + currentDateTime + ".xlsx";
		response.setHeader(headerKey,  headerValue);
		
		Company customer = service.mapToEntity(service.findById(id).orElseThrow(()-> new CustomerNotFoundException(id)));
		
		List<Shipment> shipmentList = new ArrayList<Shipment>();
				
		for (ShippingCo shipper : customer.getShippingCompanies()) {
			for(Shipment shipment : shipper.getShipments()) {
				if (shipment.getStatus() == ShipmentStatus.ORDERED) {
					shipmentList.add(shipment);
				}
			}
		}
				
		ExcelExporter exporter = new InboundReportExcelExporter(shipmentList);
		
		exporter.export(response);
		
	}
	
	@GetMapping("/api/availabilityreport/{id}")
	public void availabilityReport(HttpServletResponse response, @PathVariable Long id) throws IOException{
		
		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());
		
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=customerData_" + currentDateTime + ".xlsx";
		response.setHeader(headerKey,  headerValue);
						
		List<Product> productList = productService.findByCompanyId(id).stream().map(product -> productService.mapToEntity(product)).collect(Collectors.toList());
		
		ExcelExporter exporter = new AvailabilityReportExcelExporter(productList.stream().filter(p -> p.getUnitsInStock() > 0 && !p.isDiscontinued()).collect(Collectors.toList()));
		
		exporter.export(response);
		
	}
	
	@GetMapping("/api/fullproductreport/{id}")
	public void fullProductReport(HttpServletResponse response, @PathVariable Long id) throws IOException{
		
		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());
		
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=customerData_" + currentDateTime + ".xlsx";
		response.setHeader(headerKey,  headerValue);
						
		List<Product> productList = productService.findByCompanyId(id).stream().map(product -> productService.mapToEntity(product)).collect(Collectors.toList());
		
		ExcelExporter exporter = new AvailabilityReportExcelExporter(productList);
		
		exporter.export(response);
		
	}
}
