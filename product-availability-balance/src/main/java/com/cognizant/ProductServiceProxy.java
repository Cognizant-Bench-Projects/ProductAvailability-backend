package com.cognizant;

import com.cognizant.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "zuul-gateway", url = "localhost:8765/product-service")
public interface ProductServiceProxy {

    @GetMapping("/departments/{deptId}/products")
    public List<Product> getProductsByDept(@PathVariable int deptId);
}
