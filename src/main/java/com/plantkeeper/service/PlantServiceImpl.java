package com.plantkeeper.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.plantkeeper.business.PlantDetailView;
import com.plantkeeper.business.PlantView;
import com.plantkeeper.data.PlantTimeData;
import com.plantkeeper.dto.OrderItemDTO;
import com.plantkeeper.dto.PlantDTO;
import com.plantkeeper.entity.CustomerOrder;
import com.plantkeeper.entity.OrderItem;
import com.plantkeeper.entity.Plant;
import com.plantkeeper.repository.CategoryRepository;
import com.plantkeeper.repository.CustomerOrderRepository;
import com.plantkeeper.repository.PlantRepository;

@Service
@Transactional
public class PlantServiceImpl implements PlantService {

	@Autowired
	private PlantRepository repository;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private ProductService productService;
	@Autowired
	private CustomerOrderRepository orderRepository;
	
	public Plant mapToEntity(PlantDTO dto) {
		ModelMapper modelMapper = new ModelMapper();
		Plant plant = modelMapper.map(dto, Plant.class);
		return plant;
	}
	
	public PlantDTO mapToDTO(Plant plant) {
		ModelMapper modelMapper = new ModelMapper();
		PlantDTO dto = modelMapper.map(plant, PlantDTO.class);
		return dto;
	}
	
	@Override
	public PlantView mapToView(PlantDTO dto) {
		Optional<Plant> plantEntity = repository.findById(dto.getId());
		ModelMapper modelMapper = new ModelMapper();
		PlantView plant = modelMapper.map(dto, PlantView.class);
		plant.setCategory(categoryService.mapToView(categoryService.findById(dto.getCategoryId()).get()));
		return plant;
	}
	
	@Override
	public PlantDetailView mapToDetail(PlantDTO dto) {
		Optional<Plant> plantEntity = repository.findById(dto.getId());
		ModelMapper modelMapper = new ModelMapper();
		PlantDetailView plant = modelMapper.map(dto, PlantDetailView.class);
		plant.setCategory(categoryService.mapToView(categoryService.findById(dto.getCategoryId()).get()));
		plant.setProductCount(plantEntity.get().getProducts().stream().filter(c -> !c.isDiscontinued()).count());
		
		//TODO: Set a local arraylist, build it out, then return it to the plant
		ArrayList<PlantTimeData> timeData = new ArrayList<PlantTimeData>();
		List<CustomerOrder> orders = orderRepository.findAllByCompanyId(plantEntity.get().getCategory().getCompany().getId());
		
		return plant;
	}

	@Override
	public PlantDTO save(PlantDTO dto) {
		return mapToDTO(repository.save(mapToEntity(dto)));
	}

	@Override
	public Optional<PlantDTO> findById(Long id) {
		Optional<Plant> plant = repository.findById(id);
		if(plant.isPresent()) {
			return Optional.ofNullable(mapToDTO(plant.get()));
		} else {
			return Optional.empty();
		}
	}

	@Override
	public List<PlantDTO> findByCategoryId(Long categoryId) {
		return repository.findByCategoryId(categoryId).stream().map(this::mapToDTO).collect(Collectors.toList());
	}


	@Override
	public Boolean delete(PlantDTO dto) {
		long oldCount = repository.count();
		repository.deleteById(dto.getId());
		
		return (oldCount - repository.count() == 1);
	}

	@Override
	public List<PlantDTO> findByCompanyId(Long companyId) {
		System.out.println("Made to Impl");
		return repository.findByCompanyId(companyId).stream()
				.map(this::mapToDTO).collect(Collectors.toList());
	}	
}
