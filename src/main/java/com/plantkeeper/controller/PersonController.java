package com.plantkeeper.controller;

import java.io.UnsupportedEncodingException;
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

import com.plantkeeper.business.PersonView;
import com.plantkeeper.dto.CompanyDTO;
import com.plantkeeper.dto.PersonDTO;
import com.plantkeeper.exception.BadEmailException;
import com.plantkeeper.exception.BadEnrollmentKeyException;
import com.plantkeeper.exception.EmailTakenException;
import com.plantkeeper.service.CompanyService;
import com.plantkeeper.service.PersonService;
import com.plantkeeper.utils.PasswordHasher;

@RestController
public class PersonController {

	@Autowired
	private PersonService service;
	
	@Autowired
	private CompanyService companyService;
	
	@PostMapping("/api/register")
	private ResponseEntity<PersonView> registerPerson(@RequestBody PersonDTO dto){
		
		// Check if email is unique
		if (!service.isUnique(dto.getEmail())) throw new EmailTakenException();
		
		// TODO: Create PasswordMismatchError to throw
		if (!dto.getPassword().equals(dto.getPassword2())) return new ResponseEntity<>(null, HttpStatus.CONFLICT);
		
		// TODO: Create EnrollmentKeyNotFoundError to throw
		Optional<CompanyDTO> company = companyService.findByEnrollmentKey(dto.getEnrollmentKey());
		if (company.isEmpty()) throw new BadEnrollmentKeyException();
		
		if (dto.getEmail().isEmpty() || dto.getFirst().isEmpty() || dto.getLast().isEmpty() || dto.getPassword().isEmpty()) return new ResponseEntity<>(null, HttpStatus.FAILED_DEPENDENCY);
		
		String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		if (!dto.getEmail().matches(regex)) throw new BadEmailException();
		
		try {
			dto.setPassword(PasswordHasher.makePassword(dto.getPassword()));
		} catch (UnsupportedEncodingException e) {
			return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
		}
		return new ResponseEntity<>(service.mapToView(service.save(dto)), HttpStatus.CREATED);
	}
	
	@PostMapping("/api/login")
	private ResponseEntity<PersonView> login(@RequestBody PersonDTO dto){
		Optional<PersonDTO> person = service.findByEmail(dto.getEmail());
		if (person.isPresent()) {
			try {
				if (person.get().getPassword().equals(PasswordHasher.makePassword(dto.getPassword()))) {
					return new ResponseEntity<>(service.mapToView(person.get()), HttpStatus.OK);
				}
			} catch (UnsupportedEncodingException e) {
				return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
			} 
		}
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
	}
	
//	@PostMapping("/api/register")
//	private ResponseEntity<PersonView> register(@RequestBody PersonDTO dto){
//		if (!service.checkFieldsAreValid(dto)) {
//			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
//		} else {
//			return new ResponseEntity<>(service.mapToView(service.save(dto)), HttpStatus.CREATED);
//		}
//	}
	
	@PostMapping("/api/person")
	private ResponseEntity<PersonView> addPerson(@RequestBody PersonDTO dto){
		
		if (dto.getFirst().isEmpty() || dto.getLast().isEmpty() || dto.getEmail().isEmpty() || dto.getCompanyId() == null) return new ResponseEntity<>(null, HttpStatus.FAILED_DEPENDENCY);
		return new ResponseEntity<>(service.mapToView(service.save(dto)), HttpStatus.CREATED);
	}
	
	@GetMapping("/api/person/{id}")
	private ResponseEntity<PersonView> getPerson(@PathVariable Long id) {
		Optional<PersonDTO> person = service.findById(id);
		if (person.isPresent()) {
			return new ResponseEntity<>(service.mapToView(person.get()), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("/api/people/{id}")
	private ResponseEntity<List<PersonView>> getCompanyPeople(@PathVariable("id") Long companyId){
		List<PersonView> people = service.findByCompanyId(companyId).stream()
				.map(person -> service.mapToView(person)).collect(Collectors.toList());
		return new ResponseEntity<>(people, HttpStatus.OK);
	}
	
	@PutMapping("/api/person")
	private ResponseEntity<PersonView> editPerson(@RequestBody PersonDTO dto){
		
		Optional<CompanyDTO> company = companyService.findById(dto.getCompanyId());
		if (company.isEmpty()) { return new ResponseEntity<>(null, HttpStatus.FAILED_DEPENDENCY); }
		
		return service.findById(dto.getId()).map(person -> {
			
			person.setFirst(dto.getFirst());
			person.setLast(dto.getLast());
			person.setEmail(dto.getEmail());
			person.setPhone(dto.getPhone());
			
			// Only need to update admin status if this is a user of a company
			// Only need to update the company status if this is a user of customer
			if (company.get().getCustomerOf() != null) {
				person.setAdmin(dto.isAdmin());
			} else {
				person.setCompanyId(dto.getCompanyId());
			}
			
			return new ResponseEntity<>(service.mapToView(service.save(person)), HttpStatus.OK);
		}).orElseGet(()->{
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		});
		
	}
	
	@PutMapping("/api/updatepassword")
	private ResponseEntity<PersonView> updatePassword(@RequestBody PersonDTO dto){
		return new ResponseEntity<>(null, HttpStatus.OK);
	}
	
	@DeleteMapping("/api/person")
	private ResponseEntity<PersonView> deletePerson(@RequestBody PersonDTO dto){
		
		if (service.delete(dto)) {
			return new ResponseEntity<>(null, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(service.mapToView(dto), HttpStatus.CONFLICT);
		}
	}
}
