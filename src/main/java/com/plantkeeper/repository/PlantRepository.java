package com.plantkeeper.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.plantkeeper.entity.OrderItem;
import com.plantkeeper.entity.Plant;

public interface PlantRepository extends JpaRepository<Plant, Long> {

	Optional<Plant> findByCategoryId(Long categoryId);
	
	@Query("SELECT p FROM Plant p "
			+ "JOIN Category c ON p.category = c.id "
			+ "JOIN Company co ON c.company = co.id "
			+ "WHERE co.id = :companyId")
	List<Plant> findByCompanyId(@Param("companyId") Long companyId);
	
	@Query("SELECT oi " + 
			"FROM Plant pl JOIN Product pr ON pr.plant = pl.id " + 
			"JOIN OrderItem oi ON oi.product = pr.id " + 
			"JOIN CustomerOrder c ON oi.order = c.id " + 
			"JOIN Company co ON c.customer = co.id " + 
			"WHERE pl.id = :plantId AND co.customerOf = :companyId " +
			"ORDER BY c.created ASC")
	List<OrderItem> findOrderItemsByPlantId(@Param("plantId") Long plantId, @Param("companyId") Long companyId);
	
	@Query("SELECT p FROM Plant p JOIN Product pr ON pr.plant = p.id WHERE pr.id =?1")
	Plant findByProductId(Long id);

}
