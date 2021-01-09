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

import com.plantkeeper.business.ShipmentView;
import com.plantkeeper.dto.ShipmentDTO;
import com.plantkeeper.service.ShipmentService;
import com.plantkeeper.utils.ShipmentStatus;

@RestController
public class ShipmentController {

	@Autowired
	private ShipmentService service;

	@PostMapping("/api/shipment")
	private ResponseEntity<ShipmentView> addShipment(@RequestBody ShipmentDTO dto){
		dto.setStatus(ShipmentStatus.ORDERED);
		return new ResponseEntity<>(service.mapToView(service.save(dto)), HttpStatus.CREATED);
	}
	
	@GetMapping("/api/shipment/{id}")
	private ResponseEntity<ShipmentView> getShipment(@PathVariable Long id) {
		Optional<ShipmentDTO> shipment = service.findById(id);
		if (shipment.isPresent()) {
			return new ResponseEntity<>(service.mapToView(shipment.get()), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("/api/shipments/company/{id}")
	private ResponseEntity<List<ShipmentView>> getShipmentsByCompany(@PathVariable("id") Long companyId){
		List<ShipmentView> shipments = service.findByCompanyId(companyId).stream()
				.map(shipment -> service.mapToView(shipment)).collect(Collectors.toList());
		
		return new ResponseEntity<>(shipments, HttpStatus.OK);
	}
	
	@GetMapping("/api/shipments/shippingco/{id}")
	private ResponseEntity<List<ShipmentView>> getShipmentsByShippingCo(@PathVariable("id") Long shipperId){
		List<ShipmentView> shipments = service.findByShipperId(shipperId).stream()
				.map(shipment -> service.mapToView(shipment)).collect(Collectors.toList());
		
		return new ResponseEntity<>(shipments, HttpStatus.OK);
	}
	
	@PutMapping("/api/shipmentreceived")
	private ResponseEntity<ShipmentView> receivedShipment(@RequestBody ShipmentDTO dto){
		
		if (service.receiveShipment(dto.getId())) {
			return new ResponseEntity<>(null, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
		}		
	}
	
	@PutMapping("/api/shipment")
	private ResponseEntity<ShipmentView> editShipment(@RequestBody ShipmentDTO dto){
		
		return service.findById(dto.getId()).map(shipment -> {
			shipment.setReceived(dto.getReceived());
			shipment.setOrdered(dto.getOrdered());
			shipment.setShipperId(dto.getShipperId());
			
			return new ResponseEntity<>(service.mapToView(service.save(shipment)), HttpStatus.OK);
		}).orElseGet(()-> {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		});
	}
	
	@DeleteMapping("/api/shipment")
	private ResponseEntity<ShipmentView> deleteShipment(@RequestBody ShipmentDTO dto){
		
		if(service.delete(dto)) {
			return new ResponseEntity<>(null, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(service.mapToView(dto), HttpStatus.BAD_REQUEST);
		}
	}
}
