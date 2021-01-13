package com.plantkeeper.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.plantkeeper.business.CategoryDetailView;
import com.plantkeeper.business.CategoryView;
import com.plantkeeper.data.DataSetter;
import com.plantkeeper.dto.CategoryDTO;
import com.plantkeeper.entity.Category;
import com.plantkeeper.entity.OrderItem;
import com.plantkeeper.repository.CategoryRepository;
import com.plantkeeper.repository.CompanyRepository;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository repository;

	@Autowired
	private CompanyRepository companyRepo;

	@Autowired
	private CustomerOrderService orderService;

	private CategoryDTO mapToDTO(Category category) {
		ModelMapper modelMapper = new ModelMapper();
		CategoryDTO dto = modelMapper.map(category, CategoryDTO.class);
		return dto;
	}

	private Category mapToEntity(CategoryDTO dto) {
		ModelMapper modelMapper = new ModelMapper();
		Category category = modelMapper.map(dto, Category.class);
		category.setCompany(companyRepo.findById(dto.getCompanyId()).get());
		// TODO: Add in any connections required with the company via company service
		return category;
	}

	@Override
	public CategoryDetailView mapToDetail(CategoryDTO dto) {
		Category entity = repository.findById(dto.getId()).get();
		ModelMapper modelMapper = new ModelMapper();
		CategoryDetailView detail = modelMapper.map(dto, CategoryDetailView.class);
		detail.setPlantCount(entity.getPlants().size());

		List<OrderItem> itemList = repository.findOrderItemsByCategoryId(dto.getId(), entity.getCompany().getId());

		if (itemList.size() == 0) {
			detail.setData(null);
			detail.setLastOrder(null);
		} else {

			detail.setLastOrder(orderService
					.mapToView(orderService.findById(itemList.get(itemList.size() - 1).getOrder().getId()).get()));

			detail.setData(DataSetter.setDataList(itemList));

		}

		return detail;
	}

	@Override
	public CategoryDTO save(CategoryDTO dto) {
		System.out.println("saving");
		return mapToDTO(repository.save(mapToEntity(dto)));
	}

	@Override
	public Optional<CategoryDTO> findById(Long id) {
		Optional<Category> category = repository.findById(id);
		if (category.isPresent()) {
			return Optional.ofNullable(mapToDTO(category.get()));
		} else {
			return Optional.empty();
		}

	}

	@Override
	public CategoryView mapToView(CategoryDTO dto) {
		ModelMapper modelMapper = new ModelMapper();
		CategoryView category = modelMapper.map(dto, CategoryView.class);
		return category;
	}

	@Override
	public List<CategoryDTO> findByCompanyId(Long companyId) {
		return repository.findByCompanyId(companyId).stream().map(this::mapToDTO).collect(Collectors.toList());
	}

	@Override
	public Boolean delete(CategoryDTO dto) {
		long oldCount = repository.count();
		repository.deleteById(dto.getId());
		return (oldCount - repository.count() == 1);
	}

}
