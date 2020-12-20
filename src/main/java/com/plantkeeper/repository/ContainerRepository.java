package com.plantkeeper.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.plantkeeper.entity.Container;

public interface ContainerRepository extends JpaRepository<Container, Long> {

	List<Container> findByCompanyId(Long companyId);

}
