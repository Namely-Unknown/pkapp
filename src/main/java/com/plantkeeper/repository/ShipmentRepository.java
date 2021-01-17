package com.plantkeeper.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.plantkeeper.entity.Shipment;

public interface ShipmentRepository extends JpaRepository<Shipment, Long> {

	@Query("SELECT s FROM Shipment s "
			+ "JOIN ShippingCo sc ON s.shipper = sc.id "
			+ "JOIN Company co ON sc.company = co.id "
			+ "WHERE co.id = :companyId")
	
	List<Shipment> findByCompanyId(@Param("companyId") Long companyId);
	
	List<Shipment> findByShipperId(Long shipperId);
	
	@Query("SELECT s FROM Shipment s JOIN ShippingCo sc ON s.shipper = sc.id " + 
	"WHERE sc.company = ?1 AND s.status = 0")
	List<Shipment> findOutstandingByCompanyid(Long id);
	
}
