package com.plantkeeper.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.plantkeeper.business.ProductDetailView;
import com.plantkeeper.business.ProductView;
import com.plantkeeper.data.DataSetter;
import com.plantkeeper.dto.ProductDTO;
import com.plantkeeper.entity.OrderItem;
import com.plantkeeper.entity.Product;
import com.plantkeeper.repository.ContainerRepository;
import com.plantkeeper.repository.PlantRepository;
import com.plantkeeper.repository.ProductRepository;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository repository;
	@Autowired
	private ContainerService containerService;
	@Autowired
	private PlantService plantService;
	@Autowired
	private CustomerOrderService orderService;
	@Autowired
	private PlantRepository plantRepo;
	@Autowired
	private ContainerRepository containerRepo;
	
	public Product mapToEntity(ProductDTO dto) {
		ModelMapper modelMapper = new ModelMapper();
		Product product = modelMapper.map(dto, Product.class);
		product.setContainer(containerRepo.findByProductId(product.getId()));
		product.setPlant(plantRepo.findByProductId(product.getId()));
		return product;
	}
	
	public ProductDTO mapToDTO(Product product) {
		ModelMapper modelMapper = new ModelMapper();
		ProductDTO dto = modelMapper.map(product, ProductDTO.class);
		return dto;
	}
	
	@Override
	public ProductView mapToView(ProductDTO dto) {
		ModelMapper modelMapper = new ModelMapper();
		ProductView product = modelMapper.map(dto, ProductView.class);
		product.setPlant(plantService.mapToView(plantService.findById(dto.getPlantId()).get()));
		product.setContainer(containerService.mapToView(containerService.findById(dto.getContainerId()).get()));
		return product;
	}
	
	@Override
	public ProductDetailView mapToDetail(ProductDTO dto) {
		Product entity = repository.findById(dto.getId()).get();
		ModelMapper modelMapper = new ModelMapper();
		ProductDetailView detail = modelMapper.map(dto, ProductDetailView.class);
		detail.setPlant(plantService.mapToView(plantService.findById(dto.getPlantId()).get()));
		detail.setContainer(containerService.mapToView(containerService.findById(dto.getContainerId()).get()));
		
		List<OrderItem> itemList = repository
				.findOrderItemsByProductId(dto.getId(), entity.getContainer().getCompany().getId());
		
		if (itemList.size() == 0) {
			detail.setData(null);
		} else {
			detail.setData(DataSetter.setDataList(itemList));
			detail.setLastOrder(orderService
					.mapToView(orderService.findById(itemList.get(itemList.size() - 1).getOrder().getId()).get()));
		}
		
		return detail;
	}

	@Override
	public ProductDTO save(ProductDTO dto) {
		return mapToDTO(repository.save(mapToEntity(dto)));
	}

	@Override
	public Optional<ProductDTO> findById(Long id) {
		Optional<Product> product = repository.findById(id);
		if (product.isPresent()) {
			return Optional.ofNullable(mapToDTO(product.get()));
		} else {
			return Optional.empty();
		}
	}

	@Override
	public List<ProductDTO> findByCompanyId(Long id) {
		return repository.findByCompanyId(id).stream().map(this::mapToDTO).collect(Collectors.toList());
	}

	@Override
	public Boolean delete(ProductDTO dto) {
		long oldCount = repository.count();
		repository.deleteById(dto.getId());
		return (oldCount - repository.count() == 1);
	}

	@Override
	public int setNextSku(ProductDTO dto) {
		
		Optional<Integer> currentNum = repository.findNextSkuInt(plantService.findById(dto.getPlantId()).get().getCategoryId());
		
		if (currentNum.isEmpty()) {
			return 1;
		} else {
			return currentNum.get().intValue() + 1;
		}
	}

	@Override
	public void removeUnits(Long id, int units) {
		Product product = repository.findById(id).get();
		product.setUnitsInStock(product.getUnitsInStock() - units);
		repository.save(product);
	}

	/**
	 * Only use when coming from a plant list.  Will not return plantview
	 */
	@Override
	public ProductView mapToView(Product product) {
		//TODO: map and set container then return
		ModelMapper modelMapper = new ModelMapper();
		ProductView returnProduct = modelMapper.map(product, ProductView.class);
		returnProduct.setContainer(containerService.mapToView(product.getContainer()));
		return returnProduct;
	}

	
	
	
}
