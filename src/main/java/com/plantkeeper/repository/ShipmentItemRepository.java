package com.plantkeeper.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.plantkeeper.entity.ShipmentItem;

public interface ShipmentItemRepository extends JpaRepository<ShipmentItem, Long> {

	List<ShipmentItem> findByShipmentId(Long shipmentId);

	@Query("SELECT SUM(units) FROM ShipmentItem WHERE shipment_id = :ShipmentId")
	Optional<Integer> countByShipmentId(@Param("ShipmentId") Long shipmentId);

	@Query("SELECT SUM(unitPrice * units) AS total FROM ShipmentItem WHERE shipment_id = :ShipmentId")
	BigDecimal sumTotalByShipmentId(@Param("ShipmentId") Long id);
	
//	@Query("SELECT SUM(units) FROM ShipmentItem si JOIN Shipment s ON si.shipment = s.id WHERE s.shipper_id = :ShipperId")
//	int countByShipperId(@Param("ShipperId") Long shipperId);
//	
//	@Query("SELECT SUM(units * unitPrice) FROM ShipmentItem si JOIN Shipment s ON si.shipment = s.id WHERE s.shipper_id = :ShipperId")
//	BigDecimal sumTotalByShipperId(@Param("ShipperId") Long shipperId);
}
