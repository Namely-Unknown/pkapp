package com.plantkeeper.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.plantkeeper.entity.ShippingCo;

public interface ShippingCoRepository extends JpaRepository<ShippingCo, Long> {

	List<ShippingCo> findByCompanyId(Long companyId);
}
