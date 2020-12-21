package com.plantkeeper.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.plantkeeper.business.CustomerView;
import com.plantkeeper.dto.CompanyDTO;
import com.plantkeeper.entity.Company;
import com.plantkeeper.repository.CompanyRepository;
import com.plantkeeper.sorting.AddressMainSorting;

@Service
@Transactional
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	private CompanyRepository repository;
	
	@Autowired
	private AddressService addressService;

	public Company mapToEntity(CompanyDTO dto) {
		ModelMapper modelMapper = new ModelMapper();
		Company company = modelMapper.map(dto, Company.class);
		return company;
	}

	public CompanyDTO mapToDTO(Company company) {
		ModelMapper modelMapper = new ModelMapper();
		CompanyDTO dto = modelMapper.map(company, CompanyDTO.class);
		return dto;
	}

	@Override
	public CompanyDTO save(CompanyDTO dto) {
		return mapToDTO(repository.save(mapToEntity(dto)));
	}

	@Override
	public Optional<CompanyDTO> findById(Long id) {
		Optional<Company> company = repository.findById(id);
		if (company.isPresent()) {
			return Optional.ofNullable(mapToDTO(company.get()));
		}
		return Optional.empty();
	}

	@Override
	public CustomerView mapToView(CompanyDTO dto) {
		ModelMapper modelMapper = new ModelMapper();
		CustomerView cv = modelMapper.map(dto, CustomerView.class);
		cv.setAddresses(addressService.findByCompanyId(dto.getId()));
		cv.getAddresses().sort(new AddressMainSorting());
		return cv;
	}

	@Override
	public Boolean delete(CompanyDTO dto) {
		long oldCount = repository.count();
		repository.deleteById(dto.getId());
		return (oldCount - repository.count() == 1);
	}

	@Override
	public Optional<CompanyDTO> findByEnrollmentKey(String key) {
		Optional<Company> company = repository.findByEnrollmentKey(key);
		if (company.isPresent()) {
			return Optional.ofNullable(mapToDTO(company.get()));
		} else {
			return Optional.empty();
		}
	}

	@Override
	public List<CompanyDTO> findByCustomerOf(Long companyId) {
		return repository.findByCustomerOf(companyId).stream()
				.map(this::mapToDTO).collect(Collectors.toList());
	}

}
