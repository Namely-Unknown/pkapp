package com.plantkeeper.service;

import java.util.List;
import java.util.Optional;

import com.plantkeeper.business.PlantView;
import com.plantkeeper.dto.PlantDTO;

public interface PlantService {

	PlantDTO save(PlantDTO dto);
	Optional<PlantDTO> findById(Long id);
	List<PlantDTO> findByCategoryId(Long categoryId);
	List<PlantDTO> findByCompanyId(Long companyId);
	PlantView mapToView(PlantDTO dto);
	Boolean delete(PlantDTO dto);
}
