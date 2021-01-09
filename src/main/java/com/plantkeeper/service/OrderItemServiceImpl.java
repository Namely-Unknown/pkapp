package com.plantkeeper.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.plantkeeper.business.OrderItemView;
import com.plantkeeper.dto.OrderItemDTO;
import com.plantkeeper.dto.ProductDTO;
import com.plantkeeper.entity.OrderItem;
import com.plantkeeper.repository.OrderItemRepository;

@Service
@Transactional
public class OrderItemServiceImpl implements OrderItemService {

	@Autowired
	private OrderItemRepository repository;
	
	@Autowired
	private ProductService productService;

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
		// Need to update the product count to keep it in line
		ProductDTO product = productService.findById(dto.getProductId()).get();
		product.setUnitsInStock(product.getUnitsInStock() - dto.getUnits());
		productService.save(product);
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
		return repository.findAllByOrderId(id).stream()
				.map(this::mapToDTO).collect(Collectors.toList());
	}

	@Override
	public OrderItemView mapToView(OrderItemDTO dto) {
		ModelMapper modelMapper = new ModelMapper();
		OrderItemView item = modelMapper.map(dto, OrderItemView.class);
		item.setProduct(productService.mapToView(productService.findById(dto.getProductId()).get()));
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
		Optional<Integer> countInt = repository.invoiceCount(orderId);
		if (countInt.isPresent()) {
			return countInt.get().intValue();
		} else {
			return 0;
		}
	}

	@Override
	public BigDecimal getOrderSubtotal(Long orderId) {
		return repository.getOrderSubtotal(orderId);
	}

	@Override
	public List<OrderItemDTO> findByPlantId(Long plantId) {
		// TODO Auto-generated method stub
		return null;
	}

}
