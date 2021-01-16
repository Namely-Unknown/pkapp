package com.plantkeeper.service;

import java.util.List;
import java.util.Optional;

import com.plantkeeper.business.ContainerDetailView;
import com.plantkeeper.business.ContainerView;
import com.plantkeeper.dto.ContainerDTO;
import com.plantkeeper.entity.Container;

public interface ContainerService {

	ContainerDTO save(ContainerDTO dto);
	Optional<ContainerDTO> findById(Long id);
	ContainerView mapToView(ContainerDTO dto);
	ContainerDetailView mapToDetail(ContainerDTO dto);
	ContainerView mapToView(Container container);
	List<ContainerDTO> findByCompanyId(Long companyId);
	Boolean delete(ContainerDTO dto);
}
