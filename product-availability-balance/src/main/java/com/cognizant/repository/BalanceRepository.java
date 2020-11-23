package com.cognizant.repository;

import com.cognizant.model.Balance;
import com.cognizant.model.Location;
import com.cognizant.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BalanceRepository extends JpaRepository<Balance, Integer> {

    List<Balance> findByLocation(Location loc);
    List<Balance> findByProduct(Product prod);
    List<Balance> findByLocationAndProduct(Location loc, Product prod);
}
