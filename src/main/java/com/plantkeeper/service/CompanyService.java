package com.plantkeeper.service;

import java.util.List;
import java.util.Optional;

import com.plantkeeper.business.CustomerView;
import com.plantkeeper.dto.CompanyDTO;

public interface CompanyService {

	CompanyDTO save(CompanyDTO dto);
	List<CompanyDTO> findByCustomerOf(Long companyId);
	Optional<CompanyDTO> findByEnrollmentKey(String key);
	Optional<CompanyDTO> findById(Long id);
	CustomerView mapToView(CompanyDTO dto);
	Boolean delete(CompanyDTO dto);
}
