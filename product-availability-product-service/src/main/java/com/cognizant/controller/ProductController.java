package com.cognizant.controller;

import com.cognizant.model.Product;
import com.cognizant.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return this.productService.getAllProducts();
    }

    @GetMapping("/products/{productId}")
    public Product getProductById(@PathVariable int productId) {
        return this.productService.getProductById(productId);
    }

    @GetMapping("/departments/{deptId}/products")
    public List<Product> getProductsByDept(@PathVariable int deptId) {
        return this.productService.getProductsByDept(deptId);
    }

    @ExceptionHandler
    public ResponseEntity<String> handleException() {
        return ResponseEntity.status(500).body("Server Error");
    }
}
