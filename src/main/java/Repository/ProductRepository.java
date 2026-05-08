package com.ws101.FulgarLim.repository;

import com.ws101.FulgarLim.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository<EntityName, TypeOfPrimaryKey>
public interface ProductRepository extends JpaRepository<Product, Long> {
}