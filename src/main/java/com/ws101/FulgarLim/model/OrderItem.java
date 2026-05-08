package com.ws101.FulgarLim.EcommerceApi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.*; // ✅ ADDED: For validation rules
import com.fasterxml.jackson.annotation.JsonIgnore; // ✅ ADDED: Prevent JSON loop
import com.fasterxml.jackson.annotation.JsonIgnoreProperties; // ✅ ADDED: Fix lazy load error

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "order_items")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) // ✅ ADDED: Removes extra Hibernate data that causes errors
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Min(value = 1, message = "Quantity must be at least 1") // ✅ ADDED: Validation
    private int quantity;

    @Positive(message = "Price must be a positive number") // ✅ ADDED: Validation
    private double priceAtPurchase;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    @JsonIgnore // ✅ ADDED: Stops infinite loop & unnecessary nested data
    private Order order;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    @JsonIgnore // ✅ ADDED: Stops infinite loop & unnecessary nested data
    private Product product;
}