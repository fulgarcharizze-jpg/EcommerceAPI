package com.ws101.FulgarLim.repository;

import com.ws101.FulgarLim.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

// <Order> = your entity class, <Long> = type of its ID field
public interface OrderRepository extends JpaRepository<Order, Long> {
}