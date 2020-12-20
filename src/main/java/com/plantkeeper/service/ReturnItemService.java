package com.plantkeeper.service;

import java.util.List;
import java.util.Optional;

import com.plantkeeper.business.ReturnItemView;
import com.plantkeeper.dto.ReturnItemDTO;

public interface ReturnItemService {

	ReturnItemDTO save(ReturnItemDTO dto);
	Optional<ReturnItemDTO> findById(Long id);
	List<ReturnItemDTO> findByOrderItemId(Long orderItemId);
	List<ReturnItemDTO> findByOrderId(Long orderId);
	ReturnItemView mapToView(ReturnItemDTO dto);
	Boolean delete(ReturnItemDTO dto);
}
