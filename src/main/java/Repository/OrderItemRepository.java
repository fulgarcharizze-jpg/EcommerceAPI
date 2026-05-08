package com.ws101.FulgarLim.repository;

import com.ws101.FulgarLim.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

// <OrderItem> = your entity class, <Long> = type of its ID field
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}