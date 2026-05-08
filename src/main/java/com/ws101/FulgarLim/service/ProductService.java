package com.ws101.FulgarLim.service;

import com.ws101.FulgarLim.Product;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final List<Product> productList = new ArrayList<>();
    private Long nextId = 1L;

    public ProductService() {
        initializeSampleData();
    }

    private void initializeSampleData() {
        productList.add(new Product(nextId++, "Laptop", "Gaming laptop", 999.99, "Electronics", 10, "url1"));
        productList.add(new Product(nextId++, "Mouse", "Wireless mouse", 25.50, "Accessories", 50, "url2"));
        productList.add(new Product(nextId++, "Keyboard", "Mechanical keyboard", 89.99, "Accessories", 30, "url3"));
        productList.add(new Product(nextId++, "Monitor", "24 inch monitor", 199.99, "Electronics", 15, "url4"));
        productList.add(new Product(nextId++, "Headphones", "Noise cancelling", 150.00, "Audio", 20, "url5"));
        productList.add(new Product(nextId++, "Chair", "Ergonomic chair", 180.00, "Furniture", 8, "url6"));
        productList.add(new Product(nextId++, "Desk", "Standing desk", 250.00, "Furniture", 5, "url7"));
        productList.add(new Product(nextId++, "Webcam", "HD webcam", 45.99, "Electronics", 25, "url8"));
        productList.add(new Product(nextId++, "Microphone", "USB mic", 75.00, "Audio", 12, "url9"));
        productList.add(new Product(nextId++, "USB Drive", "128GB storage", 18.50, "Accessories", 100, "url10"));
    }

    public List<Product> getAllProducts() {
        return new ArrayList<>(productList);
    }

    // Get product by ID - returns Optional instead of null
    public Optional<Product> getProductById(Long id) {
        return productList.stream()
                .filter(product -> product.getId().equals(id))
                .findFirst();
    }

    // Create new product
    public Product createProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        product.setId(nextId++);
        productList.add(product);
        return product;
    }

    // Update product
    public Optional<Product> updateProduct(Long id, Product updatedProduct) {
        if (updatedProduct == null) {
            throw new IllegalArgumentException("Updated product cannot be null");
        }
        
        return productList.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .map(existingProduct -> {
                    int index = productList.indexOf(existingProduct);
                    updatedProduct.setId(id);
                    productList.set(index, updatedProduct);
                    return updatedProduct;
                });
    }

    // Delete product
    public boolean deleteProduct(Long id) {
        return productList.removeIf(product -> product.getId().equals(id));
    }

    // Filter by category
    public List<Product> filterByCategory(String category) {
        if (category == null || category.trim().isEmpty()) {
            return new ArrayList<>(productList);
        }
        return productList.stream()
                .filter(product -> product.getCategory().equalsIgnoreCase(category.trim()))
                .toList();
    }

    // Filter by price range
    public List<Product> filterByPrice(double minPrice, double maxPrice) {
        if (minPrice > maxPrice) {
            throw new IllegalArgumentException("Min price cannot be greater than max price");
        }
        return productList.stream()
                .filter(product -> product.getPrice() >= minPrice && product.getPrice() <= maxPrice)
                .toList();
    }

    // Filter by name
    public List<Product> filterByName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return new ArrayList<>(productList);
        }
        return productList.stream()
                .filter(product -> product.getName().toLowerCase().contains(name.trim().toLowerCase()))
                .toList();
    }

    // Additional utility methods
    public long getProductCount() {
        return productList.size();
    }

    public double getAveragePrice() {
        return productList.stream()
                .mapToDouble(Product::getPrice)
                .average()
                .orElse(0.0);
    }

    public List<Product> getLowStockProducts(int threshold) {
        return productList.stream()
                .filter(product -> product.getStock() <= threshold)
                .toList();
    }
}