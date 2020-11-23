package com.cognizant.repository;

import com.cognizant.model.Department;
import com.cognizant.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    public List<Product> findByDept(Department dept);
}
