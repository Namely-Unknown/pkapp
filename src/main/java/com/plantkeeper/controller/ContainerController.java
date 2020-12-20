package com.plantkeeper.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.plantkeeper.business.ContainerView;
import com.plantkeeper.dto.ContainerDTO;
import com.plantkeeper.exception.ContainerNotFoundException;
import com.plantkeeper.service.ContainerService;

// TODO: Update return to the proper viewModel, if needed

@RestController
public class ContainerController {

	@Autowired
	private ContainerService service;

	@PostMapping("/api/container")
	private ResponseEntity<ContainerView> addContainer(@RequestBody ContainerDTO dto) {
		return new ResponseEntity<>(service.mapToView(service.save(dto)), HttpStatus.CREATED);
	}

	@GetMapping("/api/container/{id}")
	private ResponseEntity<Optional<ContainerView>> getContainer(@PathVariable Long id) {
		Optional<ContainerDTO> container = service.findById(id);
		if (container.isPresent()) {
			return new ResponseEntity<>(Optional.ofNullable(service.mapToView(container.get())), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("/api/containers/company/{id}")
	private ResponseEntity<List<ContainerView>> getAllContainers(@PathVariable("id") Long companyId) {
		List<ContainerView> containers = service.findByCompanyId(companyId).stream()
				.map(container -> service.mapToView(container)).collect(Collectors.toList());
		return new ResponseEntity<>(containers, HttpStatus.OK);
	}

	@PutMapping("/api/container/{id}")
	private ResponseEntity<ContainerView> editContainer(@RequestBody ContainerDTO dto, @PathVariable Long id) {

		return service.findById(dto.getId()).map(container -> {
			container.setName(dto.getName());
			return new ResponseEntity<>(service.mapToView(service.save(container)), HttpStatus.OK);
		}).orElseGet(() -> {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		});
	}

	@DeleteMapping("/api/container")
	private ResponseEntity<ContainerView> deleteContainer(@RequestBody ContainerDTO dto) {

		try {
		if (service.delete(dto)) {
			return new ResponseEntity<>(null, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(service.mapToView(dto), HttpStatus.CONFLICT);
		}
		} catch (EmptyResultDataAccessException e) {
			throw new ContainerNotFoundException(dto.getId());
		}
	}

}
