package com.plantkeeper.service;

import java.util.List;
import java.util.Optional;

import com.plantkeeper.business.CategoryView;
import com.plantkeeper.dto.CategoryDTO;

public interface CategoryService {

	CategoryDTO save(CategoryDTO dto);
	Optional<CategoryDTO> findById(Long id);
	CategoryView mapToView(CategoryDTO dto);
	List<CategoryDTO> findByCompanyId(Long companyId);
	Boolean delete(CategoryDTO dto);
}
