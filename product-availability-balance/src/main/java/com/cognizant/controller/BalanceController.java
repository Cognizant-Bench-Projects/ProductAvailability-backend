package com.cognizant.controller;

import com.cognizant.ProductServiceProxy;
import com.cognizant.model.Balance;
import com.cognizant.model.Product;
import com.cognizant.service.BalanceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/available-items")
public class BalanceController {

    private BalanceService balanceService;
    private ProductServiceProxy productServiceProxy;

    public BalanceController(BalanceService balanceService, ProductServiceProxy productServiceProxy) {
        this.balanceService = balanceService;
        this.productServiceProxy = productServiceProxy;
    }

    @GetMapping
    public List<Balance> getAvailableItems(@RequestParam(value = "location") String location,
                                           @RequestParam(value = "product") String product,
                                           @RequestParam(value = "department") String department) {
        int locId = Integer.parseInt(location);
        int prodId = Integer.parseInt(product);
        int deptId = Integer.parseInt(department);

        if (locId != 0 && prodId != 0) {
            return this.balanceService.getAvailableItemsByLocationAndProduct(locId, prodId);
        }
        else if (locId != 0 && deptId != 0) {
            List<Product> productsList = this.productServiceProxy.getProductsByDept(deptId);
            return this.balanceService.getAvailableItemsByLocationAndDept(locId, productsList);
        }
        else if (prodId != 0) {
            return this.balanceService.getAvailableItemsByProduct(prodId);
        }
        else if (locId != 0) {
            return this.balanceService.getAvailableItemsByLocation(locId);
        }
        else if (deptId != 0) {
            List<Product> productsList = this.productServiceProxy.getProductsByDept(deptId);
            return this.balanceService.getAvailableItemsByDept(productsList);
        }
        else return this.balanceService.getAllAvailableItems();
    }
}
