package com.ws101.FulgarLim.service;

import com.ws101.FulgarLim.model.Product;
import com.ws101.FulgarLim.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository repo;

    public ProductService(ProductRepository repo) {
        this.repo = repo;
    }

    public List<Product> getAllProducts() {
        return repo.findAll();
    }

    public void deleteProduct(Long id) {
        repo.deleteById(id);
    }
}