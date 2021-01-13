package com.plantkeeper.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.plantkeeper.entity.Category;
import com.plantkeeper.entity.OrderItem;

public interface CategoryRepository extends JpaRepository<Category, Long> {

	List<Category> findByCompanyId(Long companyId);
	
	@Query("SELECT oi " + 
			"FROM Category cat JOIN Plant pl ON pl.category = cat.id " +
			"JOIN Product pr ON pr.plant = pl.id " + 
			"JOIN OrderItem oi ON oi.product = pr.id " + 
			"JOIN CustomerOrder c ON oi.order = c.id " + 
			"JOIN Company co ON c.customer = co.id " + 
			"WHERE cat.id = ?1 AND co.customerOf = ?2 " +
			"ORDER BY c.created ASC")
	List<OrderItem> findOrderItemsByCategoryId(Long categoryId, Long companyId);

}
