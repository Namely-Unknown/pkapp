package com.plantkeeper.service;

import java.util.List;
import java.util.Optional;

import com.plantkeeper.business.PersonView;
import com.plantkeeper.dto.PersonDTO;

public interface PersonService {

	PersonDTO save(PersonDTO dto);
	Optional<PersonDTO> findByEmail(String email);
	Optional<PersonDTO> findById(Long id);
	List<PersonDTO> findByCompanyId(Long companyId);
	PersonView mapToView(PersonDTO dto);
	Boolean delete(PersonDTO dto);
	Boolean checkFieldsAreValid(PersonDTO dto);
	Boolean isUnique(String email);
}
