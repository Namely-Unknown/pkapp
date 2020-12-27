package com.plantkeeper.repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.plantkeeper.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

	@Query("SELECT SUM(units) FROM OrderItem WHERE order_id = :orderId")
	int invoiceCount(@Param("orderId") Long orderId);
	
	@Query("SELECT SUM(units * unitPrice) FROM OrderItem WHERE order_id = :orderId")
	BigDecimal getOrderSubtotal(@Param("orderId") Long orderId);
}
