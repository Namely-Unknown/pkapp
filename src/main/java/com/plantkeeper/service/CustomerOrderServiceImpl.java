package com.plantkeeper.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.plantkeeper.business.CustomerOrderView;
import com.plantkeeper.dto.CustomerOrderDTO;
import com.plantkeeper.entity.CustomerOrder;
import com.plantkeeper.repository.CompanyRepository;
import com.plantkeeper.repository.CustomerOrderRepository;
import com.plantkeeper.repository.PersonRepository;

@Service
@Transactional
public class CustomerOrderServiceImpl implements CustomerOrderService {

	@Autowired
	private CustomerOrderRepository repository;
	
	@Autowired
	private CompanyRepository companyRepo;
	
	@Autowired
	private PersonRepository personRepo;
	
//	@Autowired
//	private OrderItemRepository orderItemRepo;
//	
//	@Autowired
//	private ReturnItemRepository returnItemRepo;
	
	public CustomerOrder mapToEntity(CustomerOrderDTO dto) {
		ModelMapper modelMapper = new ModelMapper();
		CustomerOrder customerOrder = modelMapper.map(dto, CustomerOrder.class);
		customerOrder.setCustomer(companyRepo.findById(dto.getCustomerId()).get());
		customerOrder.setPerson(personRepo.findById(dto.getPersonId()).get());
		return customerOrder;
	}
	
	public CustomerOrderDTO mapToDTO(CustomerOrder customerOrder) {
		ModelMapper modelMapper = new ModelMapper();
		CustomerOrderDTO dto = modelMapper.map(customerOrder, CustomerOrderDTO.class);
		return dto;
	}
	
	@Override
	public CustomerOrderDTO save(CustomerOrderDTO dto) {
		return mapToDTO(repository.save(mapToEntity(dto)));
	}

	@Override
	public Optional<CustomerOrderDTO> findById(Long id) {
		Optional<CustomerOrder> order = repository.findById(id);
		if (order.isPresent()) {
			return Optional.ofNullable(mapToDTO(order.get()));
		} else {
			return Optional.empty();
		}
	}

	@Override
	public Boolean delete(CustomerOrderDTO dto) {
		long oldCount = repository.count();
		repository.deleteById(dto.getId());
		
		return (oldCount - repository.count() == 1);
	}

	@Override
	public CustomerOrderView mapToView(CustomerOrderDTO dto) {
		ModelMapper modelMapper = new ModelMapper();
		CustomerOrderView order = modelMapper.map(dto, CustomerOrderView.class);
		//TODO: Set the order.subtotal = orderItemRepo calc field to return sum of order
		//TODO: Set the order.returnedAmount = orderItemRepo calc field to return sum of
		return order;
	}

	@Override
	public List<CustomerOrderDTO> findByCompanyId(Long customerId) {
		return repository.findAllByCustomerId(customerId).stream().map(this::mapToDTO).collect(Collectors.toList());
	}

}
