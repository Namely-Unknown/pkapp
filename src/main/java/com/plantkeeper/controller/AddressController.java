package com.plantkeeper.controller;

import java.util.List;
import java.util.Optional;

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

import com.plantkeeper.dto.AddressDTO;
import com.plantkeeper.service.AddressService;

@RestController
public class AddressController {

	@Autowired
	private AddressService service;

	@PostMapping("/api/address")
	private ResponseEntity<AddressDTO> addAddress(@RequestBody AddressDTO dto) {
		try {
			AddressDTO address = service.save(dto);
			return new ResponseEntity<>(address, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/api/address/{id}")
	private ResponseEntity<Optional<AddressDTO>> getAddress(@PathVariable Long id) {
		Optional<AddressDTO> address = service.findById(id);
		if (address.isPresent()) {
			return new ResponseEntity<>(address, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
	}

	// NOTE: Do not need to sort the return here because this would only be called
	// when in a client view and the addresses would be sortable
	@GetMapping("/api/addresslist/{id}")
	private ResponseEntity<List<AddressDTO>> getAllAddresses(@PathVariable("id") Long companyId) {
		return new ResponseEntity<>(service.findByCompanyId(companyId), HttpStatus.OK);
	}

	@PutMapping("/api/address/{id}")
	private ResponseEntity<AddressDTO> editAddress(@RequestBody AddressDTO dto, @PathVariable Long id) {

		return service.findById(dto.getId()).map(address -> {
			address.setIsMain(dto.getIsMain());
			address.setName(dto.getName());
			address.setStreet(dto.getStreet());
			address.setStreet2(dto.getStreet2());
			address.setCity(dto.getCity());
			address.setState(dto.getState());
			address.setZip(dto.getZip());
			return new ResponseEntity<>(service.save(address), HttpStatus.OK);
		}).orElseGet(() -> {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		});
	}
	
	/**
	 * Will not allow removal if db does not have at least 2 addresses
	 */
	@DeleteMapping("/api/address")
	private ResponseEntity<AddressDTO> delete(@RequestBody AddressDTO dto) {
		if (service.delete(dto)) {
			return new ResponseEntity<>(null, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(dto, HttpStatus.CONFLICT);
		}
	}

}
