package com.plantkeeper.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.plantkeeper.business.ProductView;
import com.plantkeeper.dto.ProductDTO;
import com.plantkeeper.entity.Product;
import com.plantkeeper.repository.ProductRepository;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository repository;
	@Autowired
	private ContainerService containerService;
	@Autowired
	private PlantService plantService;
	
	public Product mapToEntity(ProductDTO dto) {
		ModelMapper modelMapper = new ModelMapper();
		Product product = modelMapper.map(dto, Product.class);
		return product;
	}
	
	public ProductDTO mapToDTO(Product product) {
		ModelMapper modelMapper = new ModelMapper();
		ProductDTO dto = modelMapper.map(product, ProductDTO.class);
		return dto;
	}

	@Override
	public ProductDTO save(ProductDTO dto) {
		return mapToDTO(repository.save(mapToEntity(dto)));
	}

	@Override
	public Optional<ProductDTO> findById(Long id) {
		Optional<Product> product = repository.findById(id);
		if (product.isPresent()) {
			return Optional.ofNullable(mapToDTO(product.get()));
		} else {
			return Optional.empty();
		}
	}

	@Override
	public List<ProductDTO> findByCompanyId(Long id) {
		return repository.findByCompanyId(id).stream().map(this::mapToDTO).collect(Collectors.toList());
	}

	@Override
	public ProductView mapToView(ProductDTO dto) {
		ModelMapper modelMapper = new ModelMapper();
		ProductView product = modelMapper.map(dto, ProductView.class);
		product.setPlant(plantService.mapToView(plantService.findById(dto.getPlantId()).get()));
		product.setContainer(containerService.mapToView(containerService.findById(dto.getContainerId()).get()));
		return product;
	}

	@Override
	public Boolean delete(ProductDTO dto) {
		long oldCount = repository.count();
		repository.deleteById(dto.getId());
		return (oldCount - repository.count() == 1);
	}

	@Override
	public int setNextSku(ProductDTO dto) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
}
