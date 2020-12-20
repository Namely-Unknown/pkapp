package com.plantkeeper.service;

import java.util.List;
import java.util.Optional;

import com.plantkeeper.business.ShippingCoView;
import com.plantkeeper.dto.ShippingCoDTO;

public interface ShippingCoService {

	ShippingCoDTO save(ShippingCoDTO dto);
	Optional<ShippingCoDTO> findById(Long id);
	List<ShippingCoDTO> findByCompanyId(Long companyId);
	ShippingCoView mapToView(ShippingCoDTO dto);
	Boolean delete(ShippingCoDTO dto);
}
