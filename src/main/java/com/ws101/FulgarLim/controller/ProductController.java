package com.ws101.FulgarLim.controller;

import com.ws101.FulgarLim.model.Product;
import com.ws101.FulgarLim.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    // ✅ Inject Service
    private final ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // ✅ GET ALL
    @GetMapping
    public List<Product> getAll() {
        return productService.getAllProducts();
    }

    // ✅ GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getById(id));
    }

    // ✅ CREATE
    @PostMapping
    public ResponseEntity<Product> create(@RequestBody Product product) {
        return new ResponseEntity<>(productService.addProduct(product), HttpStatus.CREATED);
    }

    // ✅ UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable Long id, @RequestBody Product product) {
        return ResponseEntity.ok(productService.updateProduct(id, product));
    }

    // ✅ DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    // ✅ FILTER: BY CATEGORY
    @GetMapping("/by-category")
    public List<Product> getByCategory(@RequestParam String name) {
        return productService.getProductsByCategory(name);
    }

    // ✅ FILTER: BY PRICE RANGE
    @GetMapping("/by-price")
    public List<Product> getByPrice(@RequestParam double min, @RequestParam double max) {
        return productService.getProductsByPriceRange(min, max);
    }
}