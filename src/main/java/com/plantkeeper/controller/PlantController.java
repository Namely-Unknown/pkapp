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

import com.plantkeeper.business.PlantView;
import com.plantkeeper.dto.PlantDTO;
import com.plantkeeper.service.PlantService;

@RestController
public class PlantController {

	@Autowired
	private PlantService service;

	@PostMapping("/api/plant")
	private ResponseEntity<PlantView> addPlant(@RequestBody PlantDTO dto){
		return new ResponseEntity<>(service.mapToView(service.save(dto)), HttpStatus.CREATED);
	}
	
	@GetMapping("/api/plant/{id}")
	private ResponseEntity<PlantView> getPlant(@PathVariable Long id) {
		Optional<PlantDTO> plant = service.findById(id);
		if (plant.isPresent()) {
			return new ResponseEntity<>(service.mapToView(plant.get()), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("/api/plants/{id}")
	private ResponseEntity<List<PlantView>> getPlants(@PathVariable("id") Long categoryId) {
		List<PlantView> plants = service.findByCategoryId(categoryId).stream()
				.map(plant -> service.mapToView(plant)).collect(Collectors.toList());
		
		return new ResponseEntity<>(plants, HttpStatus.OK);
	}
	
	@PutMapping("/api/plant")
	private ResponseEntity<PlantView> editPlant(@RequestBody PlantDTO dto) {
		return service.findById(dto.getId()).map(plant -> {
			plant.setCategoryId(dto.getCategoryId());
			plant.setName(dto.getName());
			
			return new ResponseEntity<>(service.mapToView(service.save(plant)), HttpStatus.OK);
		}).orElseGet(()->{
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		});
	}
	
	@DeleteMapping("/api/plant")
	private ResponseEntity<PlantView> deletePlant(@RequestBody PlantDTO dto){
		
		if(service.delete(dto)) {
			return new ResponseEntity<>(null, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(service.mapToView(dto), HttpStatus.BAD_REQUEST);
		}
	}
}
