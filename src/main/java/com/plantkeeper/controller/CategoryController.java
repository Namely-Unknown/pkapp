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

import com.plantkeeper.business.CategoryDetailView;
import com.plantkeeper.business.CategoryView;
import com.plantkeeper.dto.CategoryDTO;
import com.plantkeeper.service.CategoryService;

@RestController
public class CategoryController {

	@Autowired
	private CategoryService service;

	@PostMapping("/api/category")
	private ResponseEntity<CategoryView> addCategory(@RequestBody CategoryDTO dto) {
		CategoryDTO category = service.save(dto);
		return new ResponseEntity<>(service.mapToView(category), HttpStatus.CREATED);
	}

	@GetMapping("/api/category/{id}")
	private ResponseEntity<Optional<CategoryDetailView>> getCategory(@PathVariable Long id) {
		Optional<CategoryDTO> category = service.findById(id);
		if (category.isPresent()) {
			return new ResponseEntity<>(Optional.ofNullable(service.mapToDetail(category.get())), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("/api/categories/company/{id}")
	private ResponseEntity<List<CategoryView>> getAllCategories(@PathVariable("id") Long companyId) {
		List<CategoryView> categories = service.findByCompanyId(companyId).stream().map(dto -> service.mapToView(dto))
				.collect(Collectors.toList());
		return new ResponseEntity<>(categories, HttpStatus.OK);
	}

	@PutMapping("/api/category")
	private ResponseEntity<CategoryView> editCategory(@RequestBody CategoryDTO dto) {

		return service.findById(dto.getId()).map(category -> {
			category.setName(dto.getName());
			category.setSkuPrefix(dto.getSkuPrefix());
			return new ResponseEntity<>(service.mapToView(service.save(category)), HttpStatus.OK);
		}).orElseGet(() -> {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		});
	}
	
	//TODO: Add check for user with admin privledges - v2
	@DeleteMapping("/api/category")
	private ResponseEntity<CategoryView> deleteCategory(@RequestBody CategoryDTO dto){
		
		if (service.delete(dto)) {
			return new ResponseEntity<>(null, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(service.mapToView(dto), HttpStatus.CONFLICT);
		}
	}

}
