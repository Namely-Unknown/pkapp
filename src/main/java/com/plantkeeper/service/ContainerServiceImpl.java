package com.plantkeeper.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.plantkeeper.business.ContainerView;
import com.plantkeeper.dto.ContainerDTO;
import com.plantkeeper.entity.Container;
import com.plantkeeper.repository.CompanyRepository;
import com.plantkeeper.repository.ContainerRepository;

@Service
@Transactional
public class ContainerServiceImpl implements ContainerService {

	@Autowired
	private ContainerRepository repository;
	
	@Autowired
	private CompanyRepository companyRepo;
	
	private Container mapToEntity(ContainerDTO dto) {
		ModelMapper modelMapper = new ModelMapper();
		Container container = modelMapper.map(dto, Container.class);
		container.setCompany(companyRepo.findById(dto.getCompanyId()).get());
		return container;
	}
	
	private ContainerDTO mapToDTO(Container container) {
		ModelMapper modelMapper = new ModelMapper();
		ContainerDTO dto = modelMapper.map(container, ContainerDTO.class);
		return dto;
	}
	
	@Override
	public ContainerDTO save(ContainerDTO dto) {
		return mapToDTO(repository.save(mapToEntity(dto)));
	}

	@Override
	public Optional<ContainerDTO> findById(Long id) {
		Optional<Container> container = repository.findById(id);
		if (container.isPresent()) {
			return Optional.ofNullable(mapToDTO(container.get()));
		} else {
			return Optional.empty();
		}
	}

	@Override
	public ContainerView mapToView(ContainerDTO dto) {
		ModelMapper modelMapper = new ModelMapper();
		ContainerView container = modelMapper.map(dto, ContainerView.class);
		return container;
	}

	@Override
	public List<ContainerDTO> findByCompanyId(Long companyId) {
		return repository.findByCompanyId(companyId).stream()
				.map(this::mapToDTO).collect(Collectors.toList());
	}

	@Override
	public Boolean delete(ContainerDTO dto) {
		long oldCount = repository.count();
		
		repository.deleteById(dto.getId());
		return (oldCount - repository.count() == 1);
	}

}
