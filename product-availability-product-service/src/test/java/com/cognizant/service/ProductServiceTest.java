package com.cognizant.service;

import com.cognizant.model.Department;
import com.cognizant.model.Product;
import com.cognizant.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ProductServiceTest {

    ProductService productService;
    ProductRepository productRepository;

    @BeforeEach
    public void setup() {
        productRepository = Mockito.mock(ProductRepository.class);
        productService = new ProductService(productRepository);
    }

    @Test
    void getAllProducts() {
        Department dept1 = new Department(1, "Shirts");
        Department dept2 = new Department(2, "Sweater");
        List<Product> expected = Arrays.asList(
                new Product(1, "Long Sleeves", dept1),
                new Product(2, "Short Sleeves", dept1),
                new Product(3, "Crew Neck", dept2)
        );

        when(productRepository.findAll()).thenReturn(expected);

        List<Product> actual = productService.getAllProducts();

        Mockito.verify(productRepository).findAll();
        assertEquals(expected, actual);
    }

    @Test
    void getProductById() {
        Department dept1 = new Department(1, "Shirts");
        Product expected = new Product(1, "Long Sleeves", dept1);

        Mockito.when(productRepository.getOne(1)).thenReturn(expected);

        Product actual = productService.getProductById(1);

        Mockito.verify(productRepository).getOne(1);
        assertEquals(expected, actual);
    }

    @Test
    void getProductsByDept_ReturnAllProductsThatBelongToDept() {
        Department dept1 = new Department(1, "Shirts");
        List<Product> expected = Arrays.asList(
                new Product(1, "Long Sleeves", dept1),
                new Product(2, "Short Sleeves", dept1)
        );

        ArgumentCaptor<Department> argument = ArgumentCaptor.forClass(Department.class);

        Mockito.when(productRepository.findByDept(argument.capture())).thenReturn(expected);

        List<Product> actual = productService.getProductsByDept(1);

        Mockito.verify(productRepository).findByDept(argument.capture());
        assertEquals(expected, actual);
    }

    @Test
    void getProductsByDept_ReturnEmptyArrayWhenNoProductBelongToDept() {
        List<Product> expected = Arrays.asList();

        ArgumentCaptor<Department> argument = ArgumentCaptor.forClass(Department.class);

        Mockito.when(productRepository.findByDept(argument.capture())).thenReturn(expected);

        List<Product> actual = productService.getProductsByDept(1);

        Mockito.verify(productRepository).findByDept(argument.capture());
        assertEquals(0, actual.size());
    }
}