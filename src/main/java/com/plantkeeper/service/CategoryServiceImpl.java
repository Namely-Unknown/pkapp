package com.plantkeeper.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.plantkeeper.business.CategoryView;
import com.plantkeeper.dto.CategoryDTO;
import com.plantkeeper.entity.Category;
import com.plantkeeper.repository.CategoryRepository;
import com.plantkeeper.repository.CompanyRepository;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository repository;
	
	@Autowired
	private CompanyRepository companyRepo;
	

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
		return repository.findByCompanyId(companyId).stream()
				.map(this::mapToDTO).collect(Collectors.toList());
	}

	@Override
	public Boolean delete(CategoryDTO dto) {
		long oldCount = repository.count();
		repository.deleteById(dto.getId());
		return (oldCount - repository.count() == 1);
	}

}
