package com.cognizant.controller;

import com.cognizant.model.Balance;
import com.cognizant.model.BalanceList;
import com.cognizant.service.BalanceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/available-items")
public class BalanceController {

    private BalanceService balanceService;

    public BalanceController(BalanceService balanceService) {
        this.balanceService = balanceService;
    }

    @GetMapping
    public BalanceList getAvailableItems(@RequestParam(value = "location") String location,
                                         @RequestParam(value = "product") String product,
                                         @RequestParam(value = "department") String department,
                                         @RequestParam(value = "page") int pageNum) {
        int locId = Integer.parseInt(location);
        int prodId = Integer.parseInt(product);
        int deptId = Integer.parseInt(department);

        if (locId != 0 && prodId != 0) {
            return this.balanceService.getAvailableItemsByLocationAndProduct(locId, prodId, pageNum);
        }
        else if (locId != 0 && deptId != 0) {
            return this.balanceService.getAvailableItemsByLocationAndDept(locId, deptId, pageNum);
        }
        else if (prodId != 0) {
            return this.balanceService.getAvailableItemsByProduct(prodId, pageNum);
        }
        else if (locId != 0) {
            return this.balanceService.getAvailableItemsByLocation(locId, pageNum);
        }
        else if (deptId != 0) {
            return this.balanceService.getAvailableItemsByDept(deptId, pageNum);
        }
        else return this.balanceService.getAllAvailableItems(pageNum);
    }
}
