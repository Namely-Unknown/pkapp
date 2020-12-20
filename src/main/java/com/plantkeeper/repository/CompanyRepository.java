package com.plantkeeper.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.plantkeeper.entity.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {

	Optional<Company> findByEnrollmentKey(String key);

}
