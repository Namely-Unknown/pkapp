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

import com.plantkeeper.business.ProductView;
import com.plantkeeper.dto.ProductDTO;
import com.plantkeeper.service.ProductService;

@RestController
public class ProductController {

	@Autowired
	private ProductService service;

	@PostMapping("/api/product")
	private ResponseEntity<ProductView> addProduct(@RequestBody ProductDTO dto){
		return new ResponseEntity<>(service.mapToView(service.save(dto)), HttpStatus.CREATED);
	}
	
	@GetMapping("/api/product/{id}")
	private ResponseEntity<ProductView> getProduct(@PathVariable Long id) {
		Optional<ProductDTO> product = service.findById(id);
		if (product.isPresent()) {
			return new ResponseEntity<>(service.mapToView(product.get()), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("/api/products/company/{id}")
	private ResponseEntity<List<ProductView>> getProductsByCompany(@PathVariable("id") Long companyId) {
		List<ProductView> products = service.findByCompanyId(companyId).stream().map(product -> service.mapToView(product)).collect(Collectors.toList());
		
		return new ResponseEntity<>(products, HttpStatus.OK);
	}
	
	@PutMapping("/api/product")
	private ResponseEntity<ProductView> editProduct(@RequestBody ProductDTO dto){
		return service.findById(dto.getId()).map(product -> {
			product.setPrice(dto.getPrice());
			
			return new ResponseEntity<>(service.mapToView(service.save(product)), HttpStatus.OK);
		}).orElseGet(()-> {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		});
	}
	
	@DeleteMapping("/api/product")
	private ResponseEntity<ProductView> deleteProduct(@RequestBody ProductDTO dto){
		if(service.delete(dto)) {
			return new ResponseEntity<>(null, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(service.mapToView(dto), HttpStatus.BAD_REQUEST);
		}
	}
}
