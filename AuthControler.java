package com.ws101.FulgarLim.controller;

import com.ws101.FulgarLim.dto.RegisterUserDto;
import com.ws101.FulgarLim.model.Product;
import com.ws101.FulgarLim.model.User;
import com.ws101.FulgarLim.repository.UserRepository;
import com.ws101.FulgarLim.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class AuthController {

    private final UserRepository userRepo;
    private final PasswordEncoder encoder;
    private final ProductService productService;

    public AuthController(UserRepository userRepo,
                          PasswordEncoder encoder,
                          ProductService productService) {
        this.userRepo = userRepo;
        this.encoder = encoder;
        this.productService = productService;
    }

    // =========================
    // 🔐 REGISTER (PUBLIC)
    // =========================
    @PostMapping("/auth/register")
    public User register(@Valid @RequestBody RegisterUserDto dto) {

        User user = new User();
        user.setUsername(dto.username);
        user.setPassword(encoder.encode(dto.password));
        user.setRole(dto.role);

        return userRepo.save(user);
    }

    // =========================
    // 📦 PRODUCTS (PUBLIC READ)
    // =========================
    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    // =========================
    // ❌ DELETE PRODUCT (ADMIN ONLY)
    // =========================
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }

    // =========================
    // 👤 GET USERS (ADMIN ONLY)
    // =========================
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/users")
    public List<User> getUsers() {
        return userRepo.findAll();
    }

    // =========================
    // ❌ DELETE USER (ADMIN ONLY)
    // =========================
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Long id) {
        userRepo.deleteById(id);
    }
}