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

import com.plantkeeper.business.ShipmentItemView;
import com.plantkeeper.dto.ShipmentItemDTO;
import com.plantkeeper.service.ShipmentItemService;

@RestController
public class ShipmentItemController {

	@Autowired
	private ShipmentItemService service;

	@PostMapping("/api/shipmentitem")
	private ResponseEntity<ShipmentItemView> addShipmentItem(@RequestBody ShipmentItemDTO dto){
		return new ResponseEntity<>(service.mapToView(service.save(dto)), HttpStatus.CREATED);
	}
	
	@GetMapping("/api/shipmentitem/{id}")
	private ResponseEntity<ShipmentItemView> getShipmentItem(@PathVariable Long id) {
		Optional<ShipmentItemDTO> shipmentItem = service.findById(id);
		if (shipmentItem.isPresent()) {
			return new ResponseEntity<>(service.mapToView(shipmentItem.get()), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("/api/shipmentitem/shipment/{id}")
	private ResponseEntity<List<ShipmentItemView>> getItemsByShipment(@PathVariable("id") Long shipmentId){
		List<ShipmentItemView> items = service.findByShipmentId(shipmentId).stream()
				.map(item -> service.mapToView(item)).collect(Collectors.toList());
		return new ResponseEntity<>(items, HttpStatus.OK);
	}
	
	@PutMapping("/api/shipmentitem")
	private ResponseEntity<ShipmentItemView> editShipmentItem(@RequestBody ShipmentItemDTO dto){
		
		return service.findById(dto.getId()).map(item -> {
			item.setProductId(dto.getProductId());
			item.setShipmentId(dto.getShipmentId());
			item.setUnitPrice(dto.getUnitPrice());
			item.setUnits(dto.getUnits());
			
			return new ResponseEntity<>(service.mapToView(service.save(item)), HttpStatus.OK);
		}).orElseGet(()->{
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		});
	}
	
	@DeleteMapping("shipmentitem")
	private ResponseEntity<ShipmentItemView> deleteShipmentItem(@RequestBody ShipmentItemDTO dto){
		if(service.delete(dto)) {
			return new ResponseEntity<>(null, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(service.mapToView(dto), HttpStatus.BAD_REQUEST);
		}
	}
}
