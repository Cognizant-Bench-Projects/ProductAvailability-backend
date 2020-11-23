package com.cognizant.service;

import com.cognizant.model.Balance;
import com.cognizant.model.Location;
import com.cognizant.model.Product;
import com.cognizant.repository.BalanceRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BalanceService {

    private BalanceRepository balanceRepository;

    public BalanceService(BalanceRepository balanceRepository) {
        this.balanceRepository = balanceRepository;
    }

    public List<Balance> getAllAvailableItems() {
        return this.balanceRepository.findAll();
    }

    public List<Balance> getAvailableItemsByLocation(int id) {
        Location loc = new Location();
        loc.setId(id);
        return this.balanceRepository.findByLocation(loc);
    }

    public List<Balance> getAvailableItemsByProduct(int id) {
        Product prod = new Product();
        prod.setId(id);
        return this.balanceRepository.findByProduct(prod);
    }

    public List<Balance> getAvailableItemsByDept(List<Product> products) {
        List<Balance> result = new ArrayList<Balance>();
        products.forEach(prod -> {
            result.addAll(this.balanceRepository.findByProduct(prod));
        });
        return result;
    }

    public List<Balance> getAvailableItemsByLocationAndProduct(int locId, int prodId) {
        Location loc = new Location();
        loc.setId(locId);
        Product prod = new Product();
        prod.setId(prodId);
        return this.balanceRepository.findByLocationAndProduct(loc, prod);
    }

    public List<Balance> getAvailableItemsByLocationAndDept(int locId, List<Product> products) {
        Location loc = new Location();
        loc.setId(locId);
        List<Balance> result = new ArrayList<Balance>();
        products.forEach(prod -> {
            result.addAll(this.balanceRepository.findByLocationAndProduct(loc, prod));
        });
        return result;
    }
}
