package com.plantkeeper.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.plantkeeper.business.ReturnItemView;
import com.plantkeeper.dto.ReturnItemDTO;
import com.plantkeeper.entity.ReturnItem;
import com.plantkeeper.repository.ReturnItemRepository;

@Service
@Transactional
public class ReturnItemServiceImpl implements ReturnItemService {

	@Autowired
	private ReturnItemRepository repository;
	
	public ReturnItem mapToEntity(ReturnItemDTO dto) {
		ModelMapper modelMapper = new ModelMapper();
		ReturnItem item = modelMapper.map(dto, ReturnItem.class);
		return item;
	}
	
	public ReturnItemDTO mapToDTO(ReturnItem item) {
		ModelMapper modelMapper = new ModelMapper();
		ReturnItemDTO dto = modelMapper.map(item, ReturnItemDTO.class);
		return dto;
	}

	@Override
	public ReturnItemDTO save(ReturnItemDTO dto) {
		return mapToDTO(repository.save(mapToEntity(dto)));
	}

	@Override
	public Optional<ReturnItemDTO> findById(Long id) {
		Optional<ReturnItem> item = repository.findById(id);
		if(item.isPresent()) {
			return Optional.ofNullable(mapToDTO(item.get()));
		} else {
			return Optional.empty();
		}
	}

	@Override
	public List<ReturnItemDTO> findByOrderItemId(Long orderItemId) {
		return repository.findAllByItemId(orderItemId).stream()
				.map(this::mapToDTO).collect(Collectors.toList());
	}

	@Override
	public List<ReturnItemDTO> findByOrderId(Long orderId) {
		return repository.findByOrderId(orderId).stream()
				.map(this::mapToDTO).collect(Collectors.toList());
	}

	@Override
	public ReturnItemView mapToView(ReturnItemDTO dto) {
		ModelMapper modelMapper = new ModelMapper();
		ReturnItemView returnItem = modelMapper.map(dto, ReturnItemView.class);
		//TODO: Add any return fields needed
		return returnItem;
	}

	@Override
	public Boolean delete(ReturnItemDTO dto) {
		long oldCount = repository.count();
		repository.deleteById(dto.getId());
		
		return (oldCount - repository.count() == 1);
	}
	
}
