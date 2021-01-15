package com.plantkeeper.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.plantkeeper.entity.LostProduct;

public interface LostProductRepository extends JpaRepository<LostProduct, Long> {

}
