package com.plantkeeper.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.plantkeeper.business.CompanyDetailView;
import com.plantkeeper.business.CompanyView;
import com.plantkeeper.business.CustomerDetailView;
import com.plantkeeper.business.CustomerView;
import com.plantkeeper.data.DataSetter;
import com.plantkeeper.dto.CompanyDTO;
import com.plantkeeper.entity.Company;
import com.plantkeeper.entity.CustomerOrder;
import com.plantkeeper.entity.OrderItem;
import com.plantkeeper.repository.CompanyRepository;
import com.plantkeeper.repository.CustomerOrderRepository;
import com.plantkeeper.repository.ShippingCoRepository;
import com.plantkeeper.sorting.AddressMainSorting;

@Service
@Transactional
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	private CompanyRepository repository;
	
	@Autowired
	private AddressService addressService;
	
	@Autowired
	private PersonService personService;
	
	@Autowired
	private CustomerOrderRepository coRepo;
	
	@Autowired
	private ShippingCoRepository shipperRepo;
	
	public Company mapToEntity(CompanyDTO dto) {
		ModelMapper modelMapper = new ModelMapper();
		Company company = modelMapper.map(dto, Company.class);
		company.setOrders(coRepo.findAllByCustomerId(company.getId()));
		company.setShippingCompanies(shipperRepo.findByCompanyId(company.getId()));
		return company;
	}

	public CompanyDTO mapToDTO(Company company) {
		ModelMapper modelMapper = new ModelMapper();
		CompanyDTO dto = modelMapper.map(company, CompanyDTO.class);
		return dto;
	}

	@Override
	public CompanyDTO save(CompanyDTO dto) {
		return mapToDTO(repository.save(mapToEntity(dto)));
	}

	@Override
	public Optional<CompanyDTO> findById(Long id) {
		Optional<Company> company = repository.findById(id);
		if (company.isPresent()) {
			return Optional.ofNullable(mapToDTO(company.get()));
		}
		return Optional.empty();
	}

	@Override
	public CustomerView mapToView(CompanyDTO dto) {
		ModelMapper modelMapper = new ModelMapper();
		CustomerView cv = modelMapper.map(dto, CustomerView.class);
		cv.setAddresses(addressService.findByCompanyId(dto.getId()));
		cv.getAddresses().sort(new AddressMainSorting());
		cv.setPeople(personService.findByCompanyId(dto.getId()).stream()
				.map(person -> personService.mapToView(person)).collect(Collectors.toList()));
		return cv;
	}

	@Override
	public Boolean delete(CompanyDTO dto) {
		long oldCount = repository.count();
		repository.deleteById(dto.getId());
		return (oldCount - repository.count() == 1);
	}

	@Override
	public Optional<CompanyDTO> findByEnrollmentKey(String key) {
		Optional<Company> company = repository.findByEnrollmentKey(key);
		if (company.isPresent()) {
			return Optional.ofNullable(mapToDTO(company.get()));
		} else {
			return Optional.empty();
		}
	}

	@Override
	public List<CompanyDTO> findByCustomerOf(Long companyId) {
		return repository.findByCustomerOf(companyId).stream()
				.map(this::mapToDTO).collect(Collectors.toList());
	}

	@Override
	public CompanyView mapToCompanyView(CompanyDTO dto) {
		ModelMapper modelMapper = new ModelMapper();
		CompanyView company = modelMapper.map(dto, CompanyView.class);
		company.setAddresses(addressService.findByCompanyId(dto.getId()));
		company.getAddresses().sort(new AddressMainSorting());
		company.setPeople(personService.findByCompanyId(dto.getId()).stream()
				.map(person -> personService.mapToView(person)).collect(Collectors.toList()));
		return company;
	}

	@Override
	public CustomerDetailView mapToDetail(CompanyDTO dto) {
		ModelMapper modelMapper = new ModelMapper();
		CustomerDetailView detail = modelMapper.map(dto, CustomerDetailView.class);
		detail.setAddresses(addressService.findByCompanyId(dto.getId()));
		detail.setPeople(personService.findByCompanyId(dto.getId()).stream()
				.map(person -> personService.mapToView(person)).collect(Collectors.toList()));
		
		List<OrderItem> itemList = repository.findOrderItemsByClientId(dto.getId());
		
		if (itemList.size() == 0) {
			detail.setData(null);
		} else {
			detail.setData(DataSetter.setCustomerDataList(itemList));
		}
		
		return detail;
		
	}

	@Override
	public CompanyDetailView mapToCompanyDetail(CompanyDTO dto) {
		ModelMapper mapper = new ModelMapper();
		CompanyDetailView detail = mapper.map(dto, CompanyDetailView.class);
		
		List<CustomerOrder> orderList = repository.findOrdersByCompanyId(dto.getId());
		List<Company> customerList = repository.findByCustomerOf(dto.getId());
		
		detail.setAddresses(addressService.findByCompanyId(dto.getId()));
		detail.setYTDRev(orderList);
		detail.setTrailingTwelveData(orderList);
		detail.setDashboardData(DataSetter.setCompanyDashboardData(customerList, orderList));
		
		return detail;
	}

	@Override
	public CompanyDTO findByOrderItemId(Long id) {
		return mapToDTO(repository.findByOrderItemId(id).get());
	}

}
