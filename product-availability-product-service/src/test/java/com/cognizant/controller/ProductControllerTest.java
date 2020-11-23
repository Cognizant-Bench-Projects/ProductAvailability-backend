package com.cognizant.controller;

import com.cognizant.model.Department;
import com.cognizant.model.Product;
import com.cognizant.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductControllerTest {

    ProductController productController;
    ProductService productService;

    @BeforeEach
    public void setUp() {
        productService = Mockito.mock(ProductService.class);
        productController = new ProductController(productService);
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

        Mockito.when(productService.getAllProducts()).thenReturn(expected);

        List<Product> actual = productController.getAllProducts();

        Mockito.verify(productService).getAllProducts();
        assertEquals(expected, actual);
    }

    @Test
    void getProductById_ReturnProductWhenProductExist() {
        Department dept1 = new Department(1, "Shirts");
        Product expected = new Product(1, "Long Sleeves", dept1);

        Mockito.when(productService.getProductById(1)).thenReturn(expected);

        Product actual = productController.getProductById(1);

        Mockito.verify(productService).getProductById(1);
        assertEquals(expected, actual);
    }

    @Test
    void getProductById_ReturnNullWhenProductNotExist() {
        Mockito.when(productService.getProductById(1)).thenReturn(null);

        Product actual = productController.getProductById(1);

        Mockito.verify(productService).getProductById(1);
        assertEquals(null, actual);
    }

    @Test
    void getProductsByDept_ReturnAllProductsThatBelongToDept() {
        Department dept1 = new Department(1, "Shirts");
        List<Product> expected = Arrays.asList(
                new Product(1, "Long Sleeves", dept1),
                new Product(2, "Short Sleeves", dept1)
        );

        Mockito.when(productService.getProductsByDept(1)).thenReturn(expected);

        List<Product> actual = productController.getProductsByDept(1);

        Mockito.verify(productService).getProductsByDept(1);
        assertEquals(expected, actual);
    }

    @Test
    void getProductsByDept_ReturnEmptyArrayWhenNoProductBelongToDept() {
        Department dept1 = new Department(1, "Shirts");
        List<Product> expected = Arrays.asList();

        Mockito.when(productService.getProductsByDept(dept1.getId())).thenReturn(expected);

        List<Product> actual = productController.getProductsByDept(dept1.getId());

        Mockito.verify(productService).getProductsByDept(dept1.getId());
        assertEquals(0, actual.size());
    }
}