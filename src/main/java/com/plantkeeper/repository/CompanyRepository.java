package com.plantkeeper.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.plantkeeper.entity.Company;
import com.plantkeeper.entity.CustomerOrder;
import com.plantkeeper.entity.OrderItem;

public interface CompanyRepository extends JpaRepository<Company, Long> {

	Optional<Company> findByEnrollmentKey(String key);
	List<Company> findByCustomerOf(Long companyId);
	
	@Query("SELECT oi " + 
			"FROM Company co JOIN CustomerOrder o ON o.customer = co.id " +
			"JOIN OrderItem oi ON oi.order = o.id " +
			"WHERE co.id = ?1 " +
			"ORDER BY o.created ASC")
	List<OrderItem> findOrderItemsByClientId(Long clientId);
	
	@Query("SELECT c FROM CustomerOrder c " + 
			"JOIN Company co ON c.customer = co.id " + 
			"WHERE co.customerOf = ?1 " +
			"ORDER BY c.created ASC")
	List<CustomerOrder> findOrdersByCompanyId(Long companyId);
}
