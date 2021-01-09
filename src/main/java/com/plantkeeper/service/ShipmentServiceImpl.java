package com.plantkeeper.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.plantkeeper.business.ShipmentView;
import com.plantkeeper.dto.ProductDTO;
import com.plantkeeper.dto.ShipmentDTO;
import com.plantkeeper.entity.Shipment;
import com.plantkeeper.entity.ShipmentItem;
import com.plantkeeper.repository.ShipmentRepository;
import com.plantkeeper.utils.ShipmentStatus;

@Service
@Transactional
public class ShipmentServiceImpl implements ShipmentService {

	@Autowired
	private ShipmentRepository repository;

	@Autowired
	private ShippingCoService shippingCoService;

	@Autowired
	private ShipmentItemService shippingItemService;
	
	@Autowired
	private ProductService productService;

	public Shipment mapToEntity(ShipmentDTO dto) {
		ModelMapper modelMapper = new ModelMapper();
		Shipment shipment = modelMapper.map(dto, Shipment.class);
		return shipment;
	}

	public ShipmentDTO mapToDTO(Shipment shipment) {
		ModelMapper modelMapper = new ModelMapper();
		ShipmentDTO dto = modelMapper.map(shipment, ShipmentDTO.class);
		return dto;
	}

	@Override
	public ShipmentDTO save(ShipmentDTO dto) {
		return mapToDTO(repository.save(mapToEntity(dto)));
	}

	@Override
	public Optional<ShipmentDTO> findById(Long id) {
		Optional<Shipment> shipment = repository.findById(id);
		if (shipment.isPresent()) {
			return Optional.ofNullable(mapToDTO(shipment.get()));
		} else {
			return Optional.empty();
		}
	}

	@Override
	public List<ShipmentDTO> findByCompanyId(Long companyId) {
		return repository.findByCompanyId(companyId).stream().map(this::mapToDTO).collect(Collectors.toList());
	}

	@Override
	public List<ShipmentDTO> findByShipperId(Long shipperId) {
		return repository.findByShipperId(shipperId).stream().map(this::mapToDTO).collect(Collectors.toList());
	}

	@Override
	public ShipmentView mapToView(ShipmentDTO dto) {
		ModelMapper modelMapper = new ModelMapper();
		ShipmentView shipment = modelMapper.map(dto, ShipmentView.class);
		shipment.setShipper(shippingCoService.mapToView(shippingCoService.findById(dto.getShipperId()).get()));
		shipment.setUnits(shippingItemService.countByShipmentId(dto.getId()));
		shipment.setCost(shippingItemService.sumTotalByShipmentId(dto.getId()));
		return shipment;
	}

	@Override
	public Boolean delete(ShipmentDTO dto) {
		long oldCount = repository.count();
		repository.deleteById(dto.getId());

		return (oldCount - repository.count() == 1);
	}

	// TODO: Review better way to complete
	@Override
	public Boolean receiveShipment(Long id) {
		Shipment shipment = repository.findById(id).get();
		if (shipment.getStatus() != ShipmentStatus.RECEIVED) {
			for (ShipmentItem item : shipment.getItems()) {
				Optional<ProductDTO> product = productService.findById(item.getProduct().getId());
				product.ifPresent(a -> {
					a.setUnitsInStock(a.getUnitsInStock() + item.getUnits());
					productService.save(a);
				});
			}
			shipment.setStatus(ShipmentStatus.RECEIVED);
			shipment.setReceived(LocalDate.now());
			repository.save(shipment);
			return true;
		}
		return false;
	}

}
