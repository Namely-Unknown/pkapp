package com.plantkeeper.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.plantkeeper.entity.ShipmentItem;

public interface ShipmentItemRepository extends JpaRepository<ShipmentItem, Long> {

	List<ShipmentItem> findByShipmentId(Long shipmentId);
}
