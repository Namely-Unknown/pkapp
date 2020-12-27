package com.plantkeeper.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.plantkeeper.business.ShipmentItemView;
import com.plantkeeper.dto.ShipmentItemDTO;
import com.plantkeeper.entity.ShipmentItem;
import com.plantkeeper.repository.ShipmentItemRepository;

@Service
@Transactional
public class ShipmentItemServiceImpl implements ShipmentItemService {

	@Autowired
	private ShipmentItemRepository repository;
	
	@Autowired
	private ProductService productService;
	
	public ShipmentItem mapToEntity(ShipmentItemDTO dto) {
		ModelMapper modelMapper = new ModelMapper();
		ShipmentItem item = modelMapper.map(dto, ShipmentItem.class);
		return item;
	}
	
	public ShipmentItemDTO mapToDTO(ShipmentItem item) {
		ModelMapper modelMapper = new ModelMapper();
		ShipmentItemDTO dto = modelMapper.map(item, ShipmentItemDTO.class);
		return dto;
	}

	@Override
	public ShipmentItemDTO save(ShipmentItemDTO dto) {
		return mapToDTO(repository.save(mapToEntity(dto)));
	}

	@Override
	public Optional<ShipmentItemDTO> findById(Long id) {
		Optional<ShipmentItem> item = repository.findById(id);
		if (item.isPresent()) {
			return Optional.ofNullable(mapToDTO(item.get()));
		} else {
			return Optional.empty();
		}
	}

	@Override
	public List<ShipmentItemDTO> findByShipmentId(Long shipmentId) {
		return repository.findByShipmentId(shipmentId).stream()
				.map(this::mapToDTO).collect(Collectors.toList());
	}

	@Override
	public ShipmentItemView mapToView(ShipmentItemDTO dto) {
		ModelMapper modelMapper = new ModelMapper();
		ShipmentItemView item = modelMapper.map(dto, ShipmentItemView.class);
		item.setProduct(productService.mapToView(productService.findById(dto.getProductId()).get()));
		return item;
	}

	@Override
	public Boolean delete(ShipmentItemDTO dto) {
		long oldCount = repository.count();
		repository.deleteById(dto.getId());
		
		return (oldCount - repository.count() == 1);
	}

	@Override
	public int countByShipmentId(Long shipmentId) {
		return repository.countByShipmentId(shipmentId);
	}

	@Override
	public BigDecimal sumTotalByShipmentId(Long id) {
		return repository.sumTotalByShipmentId(id);
	}
	
}
