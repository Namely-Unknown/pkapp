package com.plantkeeper.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.plantkeeper.entity.Container;
import com.plantkeeper.entity.OrderItem;

public interface ContainerRepository extends JpaRepository<Container, Long> {

	List<Container> findByCompanyId(Long companyId);

	@Query("SELECT oi " + "FROM Container con JOIN Product pr ON pr.container = con.id "
			+ "JOIN OrderItem oi ON oi.product = pr.id " + "JOIN CustomerOrder c ON oi.order = c.id "
			+ "JOIN Company co ON c.customer = co.id " + "WHERE con.id = ?1 AND co.customerOf = ?2 "
			+ "ORDER BY c.created ASC")
	List<OrderItem> findOrderItemsByContainerId(Long containerId, Long companyId);

	@Query("SELECT c FROM Container c JOIN Product pr ON pr.container = c.id WHERE pr.id =?1")
	Container findByProductId(Long id);
}
