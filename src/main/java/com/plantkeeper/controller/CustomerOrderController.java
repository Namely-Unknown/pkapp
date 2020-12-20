package com.plantkeeper.controller;

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
import com.plantkeeper.dto.CustomerOrderDTO;
import com.plantkeeper.service.CustomerOrderService;

@RestController
public class CustomerOrderController {

	@Autowired
	private CustomerOrderService service;
	
	@PostMapping("/api/customerorder")
	private ResponseEntity<CustomerOrderView> addOrder(@RequestBody CustomerOrderDTO dto){
		return new ResponseEntity<>(service.mapToView(service.save(dto)), HttpStatus.CREATED);
	}
	
	@GetMapping("/api/customerorder/{id}")
	private ResponseEntity<Optional<CustomerOrderDTO>> getOrder(@PathVariable Long id){
		Optional<CustomerOrderDTO> customerOrder = service.findById(id);
		if(customerOrder.isPresent()) {
			return new ResponseEntity<>(customerOrder, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("/api/customerorders/customer/{id}")
	private ResponseEntity<List<CustomerOrderView>> getAllOrders(@PathVariable("id") Long customerId){
		List<CustomerOrderView> orders = service.findByCompanyId(customerId).stream()
				.map(order -> service.mapToView(order)).collect(Collectors.toList());
		return new ResponseEntity<>(orders, HttpStatus.OK);
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
