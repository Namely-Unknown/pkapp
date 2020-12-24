package com.plantkeeper.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.plantkeeper.entity.Plant;

public interface PlantRepository extends JpaRepository<Plant, Long> {

	Optional<Plant> findByCategoryId(Long categoryId);
	
	@Query("SELECT p FROM Plant p "
			+ "JOIN Category c ON p.category = c.id "
			+ "JOIN Company co ON c.company = co.id "
			+ "WHERE co.id = :companyId")
	List<Plant> findByCompanyId(@Param("companyId") Long companyId);

}
