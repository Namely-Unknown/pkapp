package com.plantkeeper.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.plantkeeper.entity.CustomerOrder;

public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, Long> {

	List<CustomerOrder> findAllByCustomerId(Long customerId);

}
