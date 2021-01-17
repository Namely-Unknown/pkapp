package com.plantkeeper.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.plantkeeper.business.CustomerOrderView;
import com.plantkeeper.dto.CompanyDTO;
import com.plantkeeper.dto.CustomerOrderDTO;
import com.plantkeeper.service.CompanyService;
import com.plantkeeper.service.CustomerOrderService;
import com.plantkeeper.utils.OrderStatus;

@RestController
public class CustomerOrderController {

	@Autowired
	private CustomerOrderService service;
	@Autowired
	private CompanyService companyService;
	
	@PostMapping("/api/customerorder")
	private ResponseEntity<CustomerOrderView> addOrder(@RequestBody CustomerOrderDTO dto){
		dto.setStatus(OrderStatus.FULFILLED);
		Optional<CompanyDTO> company = companyService.findById(dto.getCustomerId());
		if (company.isPresent()) {
			CompanyDTO saveCompany = company.get();
			System.out.println(saveCompany.getFundsOnAccount());
			saveCompany.setFundsOnAccount(saveCompany.getFundsOnAccount().add(dto.getFoaUsed()));
			System.out.println(saveCompany.getFundsOnAccount());
			companyService.save(saveCompany);			
		}
		
		return new ResponseEntity<>(service.mapToView(service.save(dto)), HttpStatus.CREATED);
	}
	
	@GetMapping("/api/customerorder/{id}")
	private ResponseEntity<CustomerOrderView> getOrder(@PathVariable Long id){
		Optional<CustomerOrderDTO> customerOrder = service.findById(id);
		if(customerOrder.isPresent()) {
			return new ResponseEntity<>(service.mapToView(customerOrder.get()), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("/api/customerorders/customer/{id}")
	private ResponseEntity<List<CustomerOrderView>> getCustomerOrders(@PathVariable("id") Long customerId){
		List<CustomerOrderView> orders = service.findByCustomerId(customerId).stream()
				.map(order -> service.mapToView(order)).collect(Collectors.toList());
		return new ResponseEntity<>(orders, HttpStatus.OK);
	}
	
	@GetMapping("/api/customerorders/company/{id}")
	private ResponseEntity<List<CustomerOrderView>> getAllOrders(@PathVariable("id") Long companyId){
		List<CustomerOrderView> orders = service.findByCompanyId(companyId).stream()
				.map(order -> service.mapToView(order)).collect(Collectors.toList());
		return new ResponseEntity<>(orders, HttpStatus.OK);
	}
	
	@PostMapping("/api/payorder")
	private ResponseEntity<CustomerOrderView> payOrder(@RequestBody CustomerOrderDTO dto){
		Optional<CustomerOrderDTO> order = service.findById(dto.getId());
		
		if (order.isPresent()) {
			order.get().setReceived(dto.getReceived());
			order.get().setPaidDate(LocalDate.now());
			order.get().setNote(dto.getNote());
			order.get().setStatus(OrderStatus.PAID);
			return new ResponseEntity<>(service.mapToView(service.save(order.get())), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/api/customerorder")
	private ResponseEntity<CustomerOrderView> updateOrder(@RequestBody CustomerOrderDTO dto) {
		
		return service.findById(dto.getId()).map(order -> {
			order.setCustomerId(dto.getCustomerId());
			order.setPaidDate(dto.getPaidDate());
			order.setPersonId(dto.getPersonId());
			order.setPoNumber(dto.getPoNumber());
			order.setReceived(dto.getReceived());
			order.setStatus(dto.getStatus());
			return new ResponseEntity<>(service.mapToView(service.save(order)), HttpStatus.OK);
		}).orElseGet(()->{
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		});
	}
	
	@DeleteMapping("/api/customerorder")
	private ResponseEntity<CustomerOrderView> deleteOrder(@RequestBody CustomerOrderDTO dto){
		
		if (service.delete(dto)) {
			return new ResponseEntity<>(null, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(service.mapToView(dto), HttpStatus.CONFLICT);
		}
	}
}
