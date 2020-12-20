package com.plantkeeper.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.plantkeeper.entity.ReturnItem;

public interface ReturnItemRepository extends JpaRepository<ReturnItem, Long> {

	@Query("SELECT r FROM ReturnItem r "
			+ "JOIN OrderItem i ON r.item = i.id "
			+ "JOIN CustomerOrder o on i.order = o.id "
			+ "WHERE o.id = :orderId")
	List<ReturnItem> findByOrderId(@Param("orderId") Long orderId);
	
	List<ReturnItem> findAllByItemId(Long orderItemId);
	
}
