package com.plantkeeper.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.plantkeeper.entity.CustomerOrder;

public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, Long> {

	List<CustomerOrder> findAllByCustomerId(Long customerId);

	@Query("FROM CustomerOrder o JOIN Company c on o.customer = c.id WHERE c.customerOf = :companyId")
	List<CustomerOrder> findAllByCompanyId(@Param("companyId") Long companyId);
}
