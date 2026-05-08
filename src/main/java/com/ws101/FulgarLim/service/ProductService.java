package com.ws101.FulgarLim.service;

import com.ws101.FulgarLim.model.Product;
import com.ws101.FulgarLim.repository.ProductRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService {

    // Inject the repository (no more ArrayList!)
    private final ProductRepository productRepository;

    // Constructor – connects service to repository
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // --- Use built-in repository methods ---
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    // --- Use your custom queries ---
    public List<Product> getProductsByCategory(String categoryName) {
        return productRepository.findByCategoryName(categoryName);
    }

    public List<Product> getProductsByPriceRange(double min, double max) {
        return productRepository.findProductsBetweenPrice(min, max);
    }
}