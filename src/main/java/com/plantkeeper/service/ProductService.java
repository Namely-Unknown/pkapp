package com.plantkeeper.service;

import java.util.List;
import java.util.Optional;

import com.plantkeeper.business.ProductView;
import com.plantkeeper.dto.ProductDTO;

public interface ProductService {

	ProductDTO save(ProductDTO dto);
	Optional<ProductDTO> findById(Long id);
	List<ProductDTO> findByCompanyId(Long id);
	ProductView mapToView(ProductDTO dto);
	Boolean delete(ProductDTO dto);
}
