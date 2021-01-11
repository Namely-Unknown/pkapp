package com.plantkeeper.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.plantkeeper.dto.OrderItemDTO;
import com.plantkeeper.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

	@Query("SELECT SUM(units) FROM OrderItem WHERE order_id = :orderId")
	Optional<Integer> invoiceCount(@Param("orderId") Long orderId);
	
	@Query("SELECT SUM(units * unitPrice) FROM OrderItem WHERE order_id = :orderId")
	BigDecimal getOrderSubtotal(@Param("orderId") Long orderId);

	List<OrderItem> findAllByOrderId(Long id);
	
	@Query("SELECT oi " + 
			"FROM Plant pl JOIN Product pr ON pr.plant = pl.id " + 
			"JOIN OrderItem oi ON oi.product = pr.id " + 
			"JOIN CustomerOrder c ON oi.order = c.id " + 
			"JOIN Company co ON c.customer = co.id " + 
			"WHERE pl.id = :plantId AND co.customerOf = :companyId " +
			"ORDER BY c.created ASC")
	List<OrderItem> findAllByPlantId(@Param("plantId") Long plantId, @Param("companyId") Long companyId);

}
