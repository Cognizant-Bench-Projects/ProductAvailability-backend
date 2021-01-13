package com.cognizant.controller;

import com.cognizant.model.BalanceList;
import com.cognizant.service.BalanceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/available-items")
public class BalanceController {

    private BalanceService balanceService;

    public BalanceController(BalanceService balanceService) {
        this.balanceService = balanceService;
    }

    @GetMapping
    public BalanceList getAvailableItems(@RequestParam(value = "location") int locId,
                                         @RequestParam(value = "product") int prodId,
                                         @RequestParam(value = "department") int deptId,
                                         @RequestParam(value = "page") int pageNum,
                                         @RequestParam(value = "sortBy", required = false) String sortBy,
                                         @RequestParam(value = "isAscending", required = false) boolean isAscending) {

        if (locId != 0 && prodId != 0) {
            return this.balanceService.getAvailableItemsByLocationAndProduct(locId, prodId, pageNum, sortBy, isAscending);
        }
        else if (locId != 0 && deptId != 0) {
            return this.balanceService.getAvailableItemsByLocationAndDept(locId, deptId, pageNum, sortBy, isAscending);
        }
        else if (prodId != 0) {
            return this.balanceService.getAvailableItemsByProduct(prodId, pageNum, sortBy, isAscending);
        }
        else if (locId != 0) {
            return this.balanceService.getAvailableItemsByLocation(locId, pageNum, sortBy, isAscending);
        }
        else if (deptId != 0) {
            return this.balanceService.getAvailableItemsByDept(deptId, pageNum, sortBy, isAscending);
        }
        else return this.balanceService.getAllAvailableItems(pageNum, sortBy, isAscending);
    }

    @ExceptionHandler
    public ResponseEntity<String> handleException() {
        return ResponseEntity.status(500).body("Server Error");
    }
}
