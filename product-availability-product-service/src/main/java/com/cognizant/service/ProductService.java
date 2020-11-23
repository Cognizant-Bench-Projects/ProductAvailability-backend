package com.cognizant.service;

import com.cognizant.model.Department;
import com.cognizant.model.Product;
import com.cognizant.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return this.productRepository.findAll();
    }

    public Product getProductById(int id) {
        return this.productRepository.getOne(id);
    }

    public List<Product> getProductsByDept(int id) {
        Department dept = new Department();
        dept.setId(id);
        return this.productRepository.findByDept(dept);
    }
}
