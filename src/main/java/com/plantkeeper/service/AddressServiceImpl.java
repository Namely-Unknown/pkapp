package com.plantkeeper.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.plantkeeper.dto.AddressDTO;
import com.plantkeeper.entity.Address;
import com.plantkeeper.repository.AddressRepository;

// TODO: Write function to update all isMain on addresses by company to 0 prior to saving new address IF new address isMain

@Service
@Transactional
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressRepository repository;

	/*
	 * @Autowired private CompanyRepository companyRepo;
	 * 
	 * private Address mapToEntity(AddressDTO dto) { Address address = new
	 * Address(); address.setId(dto.getCompanyId());
	 * address.setStreet(dto.getStreet()); address.setStreet2(dto.getStreet2());
	 * address.setCity(dto.getCity()); address.setState(dto.getState());
	 * address.setZip(dto.getZip()); address.setName(dto.getName());
	 * address.setIsMain(dto.getIsMain());
	 * address.setCompany(companyRepo.findById(dto.getCompanyId()).get()); return
	 * address; }
	 * 
	 * private AddressView mapToView(Address address) { AddressView view = new
	 * AddressView(); CompanyViewSimple simpleCo = new CompanyViewSimple();
	 * simpleCo.setId(address.getCompany().getId());
	 * simpleCo.setName(address.getCompany().getName()); view.setCompany(simpleCo);
	 * view.setId(address.getId()); view.setStreet(address.getStreet());
	 * view.setStreet2(address.getStreet2()); view.setCity(address.getCity());
	 * view.setState(address.getState()); view.setZip(address.getZip());
	 * view.setName(address.getName()); view.setIsMain(address.getIsMain());
	 * 
	 * return view; }
	 * 
	 */

	public Address mapToEntity(AddressDTO dto) {
		ModelMapper modelMapper = new ModelMapper();
		Address address = modelMapper.map(dto, Address.class);
		return address;
	}

	public AddressDTO mapToDTO(Address address) {
		ModelMapper modelMapper = new ModelMapper();
		AddressDTO dto = modelMapper.map(address, AddressDTO.class);
		return dto;
	}

	@Override
	public AddressDTO save(AddressDTO dto) {
		return mapToDTO(repository.save(mapToEntity(dto)));
	}

	@Override
	public Optional<AddressDTO> findById(Long id) {
		Optional<Address> address = repository.findById(id);
		if (address.isPresent()) {
			return Optional.ofNullable(mapToDTO(address.get()));
		} else {
			return Optional.empty();
		}
	}

	@Override
	public List<AddressDTO> findByCompanyId(Long companyId) {
		return repository.findByCompanyId(companyId).stream().map(this::mapToDTO).collect(Collectors.toList());
	}

	/**
	 * Will not allow removal if db does not have at least 2 addresses
	 */
	@Override
	public Boolean delete(AddressDTO dto) {
		if (repository.countByCompanyId(dto.getCompanyId()) > 1) {
			long oldCount = repository.count();
			repository.deleteById(dto.getId());
			
			return (oldCount - repository.count() == 1);
		} else {
			return false;
		}
	}
}
