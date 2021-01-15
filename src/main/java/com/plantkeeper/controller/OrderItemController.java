package com.plantkeeper.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.plantkeeper.business.OrderItemView;
import com.plantkeeper.dto.OrderItemDTO;
import com.plantkeeper.service.OrderItemService;

@RestController
public class OrderItemController {

	@Autowired
	private OrderItemService service;
	
	@PostMapping("/api/orderitem")
	private ResponseEntity<OrderItemView> addOrderItem(@RequestBody OrderItemDTO dto) {
		return new ResponseEntity<>(service.mapToView(service.save(dto)), HttpStatus.CREATED);
	}

	@GetMapping("/api/orderitem/{id}")
	private ResponseEntity<Optional<OrderItemDTO>> getOrderItem(@PathVariable Long id) {
		Optional<OrderItemDTO> orderItem = service.findById(id);
		if (orderItem.isPresent()) {
			return new ResponseEntity<>(orderItem, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("/api/orderitems/order/{id}")
	private ResponseEntity<List<OrderItemView>> getOrderItems(@PathVariable("id") Long orderId) {
		List<OrderItemView> items = service.findByOrderId(orderId).stream()
				.map(item -> service.mapToView(item)).collect(Collectors.toList());
		return new ResponseEntity<>(items, HttpStatus.OK);
	}
	
}
