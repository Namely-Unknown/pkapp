package com.plantkeeper.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.plantkeeper.entity.Plant;

public interface PlantRepository extends JpaRepository<Plant, Long> {

	Optional<Plant> findByCategoryId(Long categoryId);

}
