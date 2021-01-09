package com.plantkeeper.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import com.plantkeeper.business.OrderItemView;
import com.plantkeeper.dto.OrderItemDTO;

public interface OrderItemService {

	OrderItemDTO save(OrderItemDTO dto);
	Optional<OrderItemDTO> findById(Long id);
	List<OrderItemDTO> findByOrderId(Long id);
	OrderItemView mapToView(OrderItemDTO dto);
	Boolean delete(OrderItemDTO dto);
	int invoiceCount(Long orderId);
	BigDecimal getOrderSubtotal(Long orderId);
	List<OrderItemDTO> findByPlantId(Long plantId);
}
