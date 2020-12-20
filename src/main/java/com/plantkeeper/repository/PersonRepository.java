package com.plantkeeper.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.plantkeeper.entity.Person;

public interface PersonRepository extends JpaRepository<Person, Long>{

	List<Person> findByCompanyId(Long companyId);
	Optional<Person> findByEmail(String email);

}
