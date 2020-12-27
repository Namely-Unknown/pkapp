package com.plantkeeper.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.plantkeeper.business.OrderItemView;
import com.plantkeeper.dto.OrderItemDTO;
import com.plantkeeper.entity.OrderItem;
import com.plantkeeper.repository.OrderItemRepository;

@Service
@Transactional
public class OrderItemServiceImpl implements OrderItemService {

	@Autowired
	private OrderItemRepository repository;

	public OrderItem mapToEntity(OrderItemDTO dto) {
		ModelMapper modelMapper = new ModelMapper();
		OrderItem item = modelMapper.map(dto, OrderItem.class);
		return item;
	}

	public OrderItemDTO mapToDTO(OrderItem item) {
		ModelMapper modelMapper = new ModelMapper();
		OrderItemDTO dto = modelMapper.map(item, OrderItemDTO.class);
		return dto;
	}

	@Override
	public OrderItemDTO save(OrderItemDTO dto) {
		return mapToDTO(repository.save(mapToEntity(dto)));
	}

	@Override
	public Optional<OrderItemDTO> findById(Long id) {
		Optional<OrderItem> item = repository.findById(id);
		if (item.isPresent()) {
			return Optional.ofNullable(mapToDTO(item.get()));
		} else {
			return Optional.empty();
		}
	}

	@Override
	public List<OrderItemDTO> findByOrderId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderItemView mapToView(OrderItemDTO dto) {
		ModelMapper modelMapper = new ModelMapper();
		OrderItemView item = modelMapper.map(dto, OrderItemView.class);
		// TODO: calculate fields as needed
		return item;
	}

	@Override
	public Boolean delete(OrderItemDTO dto) {
		long oldCount = repository.count();
		repository.deleteById(dto.getId());

		return (oldCount - repository.count() == 1);
	}

	@Override
	public int invoiceCount(Long orderId) {
		return repository.invoiceCount(orderId);
	}

	@Override
	public BigDecimal getOrderSubtotal(Long orderId) {
		return repository.getOrderSubtotal(orderId);
	}

}
