package com.plantkeeper.service;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.plantkeeper.dto.LostProductDTO;
import com.plantkeeper.entity.LostProduct;
import com.plantkeeper.repository.LostProductRepository;

@Service
@Transactional
public class LostProductServiceImpl implements LostProductService {

	@Autowired
	private LostProductRepository repository;
	
	public LostProduct mapToEntity(LostProductDTO dto) {
		ModelMapper mapper = new ModelMapper();
		return mapper.map(dto, LostProduct.class);
	}
	
	public LostProductDTO mapToDTO(LostProduct product) {
		ModelMapper mapper = new ModelMapper();
		return mapper.map(product, LostProductDTO.class);
	}
	
	@Override
	public LostProductDTO save(LostProductDTO dto) {
		return mapToDTO(repository.save(mapToEntity(dto)));
	}

}
