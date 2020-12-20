package com.plantkeeper.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.plantkeeper.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

	List<Address> findByCompanyId(Long companyId);

	int countByCompanyId(Long companyId);

}
