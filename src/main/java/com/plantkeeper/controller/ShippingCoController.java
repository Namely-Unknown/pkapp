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

import com.plantkeeper.business.ShippingCoView;
import com.plantkeeper.dto.ShippingCoDTO;
import com.plantkeeper.service.ShippingCoService;

@RestController
public class ShippingCoController {

	@Autowired
	private ShippingCoService service;

	@PostMapping("/api/shippingco")
	private ResponseEntity<ShippingCoView> addShippingCo(@RequestBody ShippingCoDTO dto) {
		return new ResponseEntity<>(service.mapToView(service.save(dto)), HttpStatus.CREATED);
	}

	@GetMapping("/api/shippingco/{id}")
	private ResponseEntity<ShippingCoView> getShippingCo(@PathVariable Long id) {
		Optional<ShippingCoDTO> shippingCo = service.findById(id);
		if (shippingCo.isPresent()) {
			return new ResponseEntity<>(service.mapToView(shippingCo.get()), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("/api/shippingco/company/{id}")
	private ResponseEntity<List<ShippingCoView>> getShippersByCompany(@PathVariable("id") Long companyId) {
		List<ShippingCoView> shippers = service.findByCompanyId(companyId).stream()
				.map(shipper -> service.mapToView(shipper)).collect(Collectors.toList());

		return new ResponseEntity<>(shippers, HttpStatus.OK);
	}

	@PutMapping("/api/shippingco")
	private ResponseEntity<ShippingCoView> editShippingCo(@RequestBody ShippingCoDTO dto) {

		return service.findById(dto.getId()).map(shipper -> {
			shipper.setName(dto.getName());
			shipper.setState(dto.getState());

			return new ResponseEntity<>(service.mapToView(service.save(shipper)), HttpStatus.OK);
		}).orElseGet(() -> {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		});
	}

	@DeleteMapping("/api/shippingco")
	private ResponseEntity<ShippingCoView> deleteShippingCo(@RequestBody ShippingCoDTO dto) {
		System.out.println(dto);
		if (service.delete(dto)) {
			return new ResponseEntity<>(null, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(service.mapToView(dto), HttpStatus.BAD_REQUEST);
		}
	}
}
