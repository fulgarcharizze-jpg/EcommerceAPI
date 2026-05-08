package com.ws101.FulgarLim.EcommerceApi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import jakarta.validation.constraints.*; // ✅ ADDED: Validation rules
import com.fasterxml.jackson.annotation.JsonIgnore; // ✅ ADDED: Prevent JSON loop
import com.fasterxml.jackson.annotation.JsonIgnoreProperties; // ✅ ADDED: Fix lazy load error

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "categories")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) // ✅ ADDED: Remove extra Hibernate data
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Category name is required") // ✅ ADDED: Validation
    @Size(min = 2, max = 50, message = "Name must be 2–50 characters") // ✅ ADDED: Validation
    private String name;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore // ✅ ADDED: Stop infinite loop (Category → Product → Category...)
    private List<Product> products;
}