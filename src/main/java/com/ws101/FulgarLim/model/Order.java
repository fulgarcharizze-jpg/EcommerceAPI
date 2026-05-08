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
import java.time.LocalDate;
import java.util.List;
import jakarta.validation.constraints.*; // ✅ ADDED: Validation rules
import com.fasterxml.jackson.annotation.JsonIgnore; // ✅ ADDED: Prevent JSON loop
import com.fasterxml.jackson.annotation.JsonIgnoreProperties; // ✅ ADDED: Fix lazy loading error

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) // ✅ ADDED: Remove extra Hibernate data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Order number is required") // ✅ ADDED: Validation
    private String orderNumber;

    private LocalDate orderDate;

    @Positive(message = "Total amount must be a positive number") // ✅ ADDED: Validation
    private double totalAmount;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore // ✅ ADDED: Stop infinite loop (Order → OrderItem → Order...)
    private List<OrderItem> orderItems;
}