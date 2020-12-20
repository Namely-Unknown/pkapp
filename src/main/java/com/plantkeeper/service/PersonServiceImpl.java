package com.plantkeeper.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.plantkeeper.business.PersonView;
import com.plantkeeper.dto.PersonDTO;
import com.plantkeeper.entity.Person;
import com.plantkeeper.repository.PersonRepository;

@Service
@Transactional
public class PersonServiceImpl implements PersonService {

	@Autowired
	private PersonRepository repository;
	
	public Person mapToEntity(PersonDTO dto) {
		ModelMapper modelMapper = new ModelMapper();
		Person person = modelMapper.map(dto, Person.class);
		// TODO: Make connections, if necessary
		return person;
	}
	
	public PersonDTO mapToDTO(Person person) {
		ModelMapper modelMapper = new ModelMapper();
		PersonDTO dto = modelMapper.map(person, PersonDTO.class);
		return dto;
	}

	@Override
	public PersonDTO save(PersonDTO dto) {
		return mapToDTO(repository.save(mapToEntity(dto)));
	}

	@Override
	public Optional<PersonDTO> findById(Long id) {
		Optional<Person> person = repository.findById(id);
		if(person.isPresent()) {
			return Optional.ofNullable(mapToDTO(person.get()));
		} else {
			return Optional.empty();
		}
	}

	@Override
	public List<PersonDTO> findByCompanyId(Long companyId) {
		return repository.findByCompanyId(companyId).stream().map(this::mapToDTO).collect(Collectors.toList());
	}

	@Override
	public PersonView mapToView(PersonDTO dto) {
		ModelMapper modelMapper = new ModelMapper();
		PersonView person = modelMapper.map(dto, PersonView.class);
		// TODO: Add and calc fields, as needed
		return person;
	}

	@Override
	public Boolean delete(PersonDTO dto) {
		long oldCount = repository.count();
		repository.deleteById(dto.getId());
		
		return (oldCount - repository.count() == 1);
	}

	@Override
	public Boolean checkFieldsAreValid(PersonDTO dto) {
		String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
	    
	    
	    if (dto.getFirst().isEmpty() || dto.getLast().isEmpty() || dto.getCompanyId() == null) return false;
	    if (!dto.getPassword().equals(dto.getPassword2())) return false;
	    return dto.getEmail().matches(regex);
	}

	@Override
	public Optional<PersonDTO> findByEmail(String email) {
		Optional<Person> person = repository.findByEmail(email);
		if(person.isPresent()) {
			return Optional.ofNullable(mapToDTO(person.get()));
		} else {
			return Optional.empty();
		}
	}

	@Override
	public Boolean isUnique(String email) {
		Optional<Person> person = repository.findByEmail(email);
		if (person.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}
	
}
