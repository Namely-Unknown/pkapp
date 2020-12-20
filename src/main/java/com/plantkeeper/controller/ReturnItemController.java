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

import com.plantkeeper.business.ReturnItemView;
import com.plantkeeper.dto.ReturnItemDTO;
import com.plantkeeper.service.ReturnItemService;

@RestController
public class ReturnItemController {

	@Autowired
	private ReturnItemService service;

	@PostMapping("/api/returnitem")
	private ResponseEntity<ReturnItemView> addReturnItem(@RequestBody ReturnItemDTO dto) {
		if (dto.getCreated() != null && dto.getOrderItemId() != null && dto.getUnits() > 0) {
			return new ResponseEntity<>(service.mapToView(service.save(dto)), HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/api/returnitem/{id}")
	private ResponseEntity<ReturnItemView> getReturnItem(@PathVariable Long id) {
		Optional<ReturnItemDTO> returnItem = service.findById(id);
		if (returnItem.isPresent()) {
			return new ResponseEntity<>(service.mapToView(returnItem.get()), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("/api/returnitems/orderitem/{id}")
	private ResponseEntity<List<ReturnItemView>> getReturnItemByOrderItem(@PathVariable("id") Long orderItemId) {
		List<ReturnItemView> items = service.findByOrderItemId(orderItemId).stream()
				.map(returnItem -> service.mapToView(returnItem)).collect(Collectors.toList());

		return new ResponseEntity<>(items, HttpStatus.OK);
	}

	@GetMapping("/api/returnitems/order/{id}")
	private ResponseEntity<List<ReturnItemView>> getReturnItemByOrder(@PathVariable("id") Long orderId) {
		List<ReturnItemView> items = service.findByOrderId(orderId).stream()
				.map(returnItem -> service.mapToView(returnItem)).collect(Collectors.toList());

		return new ResponseEntity<>(items, HttpStatus.OK);
	}
	
	@PutMapping("/api/returnitem")
	private ResponseEntity<ReturnItemView> editReturnItem(@RequestBody ReturnItemDTO dto){
		return service.findById(dto.getId()).map(returnItem -> {
			returnItem.setOrderItemId(dto.getOrderItemId());
			returnItem.setUnits(dto.getUnits());
			
			return new ResponseEntity<>(service.mapToView(service.save(returnItem)), HttpStatus.OK);
		}).orElseGet(() -> {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		});
	}
	
	@DeleteMapping("/api/returnitem")
	private ResponseEntity<ReturnItemView> deleteReturnItem(@RequestBody ReturnItemDTO dto){
		if(service.delete(dto)) {
			return new ResponseEntity<>(null, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(service.mapToView(dto), HttpStatus.BAD_REQUEST);
		}
	}
}
