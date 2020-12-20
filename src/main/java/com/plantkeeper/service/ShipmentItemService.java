package com.plantkeeper.service;

import java.util.List;
import java.util.Optional;

import com.plantkeeper.business.ShipmentItemView;
import com.plantkeeper.dto.ShipmentItemDTO;

public interface ShipmentItemService {

	ShipmentItemDTO save(ShipmentItemDTO dto);
	Optional<ShipmentItemDTO> findById(Long id);
	List<ShipmentItemDTO> findByShipmentId(Long shipmentId);
	ShipmentItemView mapToView(ShipmentItemDTO dto);
	Boolean delete(ShipmentItemDTO dto);
	
}
