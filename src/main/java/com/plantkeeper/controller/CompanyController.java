package com.plantkeeper.controller;

import java.math.BigDecimal;
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

import com.plantkeeper.business.CompanyDetailView;
import com.plantkeeper.business.CompanyView;
import com.plantkeeper.business.CustomerDetailView;
import com.plantkeeper.business.CustomerView;
import com.plantkeeper.dto.CompanyDTO;
import com.plantkeeper.service.AddressService;
import com.plantkeeper.service.CompanyService;
import com.plantkeeper.utils.KeyGenerator;

//TODO: Update returns to companyView

@RestController
public class CompanyController {

	@Autowired
	private CompanyService service;
	
	@Autowired
	private AddressService addressService;

	@PostMapping("/api/company")
	private ResponseEntity<CompanyDTO> addCompany(@RequestBody CompanyDTO dto) {
		try {
			if (dto.getCustomerOf() == null) {
				String key = KeyGenerator.generateKey();
				dto.setEnrollmentKey(key);
			}
			dto.setFundsOnAccount(new BigDecimal("0.00"));
			CompanyDTO company = service.save(dto);
			return new ResponseEntity<>(company, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/api/companydetail/{id}")
	private ResponseEntity<CompanyDetailView> getCompanyDetail(@PathVariable Long id) {
		Optional<CompanyDTO> company = service.findById(id);
		if (company.isPresent()) {
			return new ResponseEntity<>(service.mapToCompanyDetail(company.get()), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("/api/company/{id}")
	private ResponseEntity<CompanyView> getCompany(@PathVariable Long id) {
		Optional<CompanyDTO> company = service.findById(id);
		if (company.isPresent()) {
			return new ResponseEntity<>(service.mapToCompanyView(company.get()), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("/api/customer/{id}")
	private ResponseEntity<Optional<CustomerDetailView>> getCustomer(@PathVariable long id) {
		Optional<CompanyDTO> company = service.findById(id);
		if (company.isPresent()) {
			return new ResponseEntity<>(Optional.ofNullable(service.mapToDetail(company.get())), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("/api/customers/{id}")
	private ResponseEntity<List<CustomerView>> getAllCustomers(@PathVariable("id") Long companyId) {
		
		List<CustomerView> customers = service.findByCustomerOf(companyId).stream()
				.map(customer -> service.mapToView(customer)).collect(Collectors.toList());
		
		for (CustomerView c : customers) {
			c.setAddresses(addressService.findByCompanyId(c.getId()));
		}
		
		return new ResponseEntity<>(customers, HttpStatus.OK);
	}

	// TODO: When returning a company, will get DTO from the service layer. Then
	// need to request a view from the view layer

	// This is best because it allows my controller to determine what will and won't
	// be updated on a change
	@PutMapping("/api/company")
	private ResponseEntity<CompanyDTO> editCompany(@RequestBody CompanyDTO dto) {

		return service.findById(dto.getId()).map(company -> {
			company.setName(dto.getName());
			company.setShipping(dto.getShipping());
			return new ResponseEntity<>(service.save(company), HttpStatus.OK);
		}).orElseGet(() -> {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		});
	}
	
	@DeleteMapping("/api/company")
	private ResponseEntity<CompanyDTO> deleteCompany(@RequestBody CompanyDTO dto){
		
		if(service.delete(dto)) {
			return new ResponseEntity<>(null, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(dto, HttpStatus.CONFLICT);
		}
	}
	
	
	

}
