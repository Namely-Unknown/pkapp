package com.plantkeeper.service;

import java.util.List;
import java.util.Optional;

import com.plantkeeper.business.ShipmentView;
import com.plantkeeper.dto.ShipmentDTO;

public interface ShipmentService {

	ShipmentDTO save(ShipmentDTO dto);
	Optional<ShipmentDTO> findById(Long id);
	List<ShipmentDTO> findByCompanyId(Long companyId);
	List<ShipmentDTO> findByShipperId(Long shipperId);
	ShipmentView mapToView(ShipmentDTO dto);
	Boolean delete(ShipmentDTO dto);
}
