package com.plantkeeper.service;

import java.util.List;
import java.util.Optional;

import com.plantkeeper.dto.AddressDTO;

public interface AddressService {

	AddressDTO save(AddressDTO dto); // TODO: Need to remove any primary tags from other addresses if this one is meant to be primary
	Optional<AddressDTO> findById(Long id);
	List<AddressDTO> findByCompanyId(Long companyId);
	Boolean delete(AddressDTO dto);
}
