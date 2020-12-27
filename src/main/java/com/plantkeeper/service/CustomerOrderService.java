package com.plantkeeper.service;

import java.util.List;
import java.util.Optional;

import com.plantkeeper.business.CustomerOrderView;
import com.plantkeeper.dto.CustomerOrderDTO;

public interface CustomerOrderService {

	CustomerOrderDTO save(CustomerOrderDTO dto);
	List<CustomerOrderDTO> findByCustomerId(Long customerId);
	List<CustomerOrderDTO> findByCompanyId(Long companyId);
	Optional<CustomerOrderDTO> findById(Long id);
	CustomerOrderView mapToView(CustomerOrderDTO dto);
	Boolean delete(CustomerOrderDTO dto);

}
