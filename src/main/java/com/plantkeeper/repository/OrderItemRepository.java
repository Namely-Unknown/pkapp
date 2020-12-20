package com.plantkeeper.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.plantkeeper.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
