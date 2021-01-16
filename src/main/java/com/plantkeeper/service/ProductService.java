package com.plantkeeper.service;

import java.util.List;
import java.util.Optional;

import com.plantkeeper.business.ProductDetailView;
import com.plantkeeper.business.ProductView;
import com.plantkeeper.dto.ProductDTO;
import com.plantkeeper.entity.Product;

public interface ProductService {

	ProductDTO save(ProductDTO dto);
	Optional<ProductDTO> findById(Long id);
	List<ProductDTO> findByCompanyId(Long id);
	ProductView mapToView(ProductDTO dto);
	ProductDetailView mapToDetail(ProductDTO dto);
	ProductView mapToView(Product product);
	Boolean delete(ProductDTO dto);
	int setNextSku(ProductDTO dto);
	void removeUnits(Long id, int units);
}
