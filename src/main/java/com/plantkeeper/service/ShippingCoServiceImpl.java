package com.plantkeeper.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.plantkeeper.business.ShippingCoView;
import com.plantkeeper.dto.ShippingCoDTO;
import com.plantkeeper.entity.ShippingCo;
import com.plantkeeper.repository.ShippingCoRepository;

@Service
@Transactional
public class ShippingCoServiceImpl implements ShippingCoService {

	@Autowired
	private ShippingCoRepository repository;
	
	public ShippingCo mapToEntity(ShippingCoDTO dto) {
		ModelMapper modelMapper = new ModelMapper();
		ShippingCo shippingCo = modelMapper.map(dto, ShippingCo.class);
		return shippingCo;
	}
	
	public ShippingCoDTO mapToDTO(ShippingCo shippingCo) {
		ModelMapper modelMapper = new ModelMapper();
		ShippingCoDTO dto = modelMapper.map(shippingCo, ShippingCoDTO.class);
		return dto;
	}

	@Override
	public ShippingCoDTO save(ShippingCoDTO dto) {
		return mapToDTO(repository.save(mapToEntity(dto)));
	}

	@Override
	public Optional<ShippingCoDTO> findById(Long id) {
		Optional<ShippingCo> shippingCo = repository.findById(id);
		
		if (shippingCo.isPresent()) {
			return Optional.ofNullable(mapToDTO(shippingCo.get()));
		} else {
			return Optional.empty();
		}
	}

	@Override
	public List<ShippingCoDTO> findByCompanyId(Long companyId) {
		return repository.findByCompanyId(companyId).stream()
				.map(this::mapToDTO).collect(Collectors.toList());
	}

	@Override
	public ShippingCoView mapToView(ShippingCoDTO dto) {
		ModelMapper modelMapper = new ModelMapper();
		ShippingCoView shipper = modelMapper.map(dto, ShippingCoView.class);
		//TODO: Add any necessary extra data fields
		return shipper;
	}

	@Override
	public Boolean delete(ShippingCoDTO dto) {
		long oldCount = repository.count();
		repository.deleteById(dto.getId());
		
		return (oldCount - repository.count() == 1);
	}
	
}
