package com.plantkeeper.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.plantkeeper.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	@Query("SELECT p FROM Product p "
			+ "JOIN Container c ON p.container = c.id "
			+ "JOIN Company co ON c.company = co.id "
			+ "WHERE co.id = :companyId ORDER BY p.id ASC")
	List<Product> findByCompanyId(@Param("companyId") Long id);

}
