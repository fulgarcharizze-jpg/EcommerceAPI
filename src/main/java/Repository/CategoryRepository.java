package com.ws101.FulgarLim.repository;

import com.ws101.FulgarLim.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}