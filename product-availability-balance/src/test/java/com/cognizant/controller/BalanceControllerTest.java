package com.cognizant.controller;

import com.cognizant.model.*;
import com.cognizant.service.BalanceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class BalanceControllerTest {

    BalanceController balanceController;
    BalanceService balanceService;

    Department dept1 = new Department(1, "Shirts");
    Department dept2 = new Department(2, "Sweater");
    Department dept3 = new Department(3, "Pants");

    Product prod1 = new Product(1, "Long Sleeves", dept1);
    Product prod2 = new Product(2, "Short Sleeves", dept1);
    Product prod3 = new Product(3, "Crew Neck", dept2);
    Product prod4 = new Product(4, "Sweatpants", dept3);
    Product prod5 = new Product(5, "Casual Pants", dept3);

    Location loc1 = new Location(1, "Irving", "75063");
    Location loc2 = new Location(2, "New York", "10024");
    Location loc3 = new Location(3, "Glendale", "91201");

    List<Balance> mockBalances = Arrays.asList(
            new Balance(new BalanceId(1, 1), prod1, loc1, 10),
            new Balance(new BalanceId(1, 2), prod1, loc2, 18),
            new Balance(new BalanceId(1, 3), prod1, loc3, 20),
            new Balance(new BalanceId(2, 1), prod2, loc1, 6),
            new Balance(new BalanceId(3, 1), prod3, loc1, 1),
            new Balance(new BalanceId(3, 3), prod3, loc3, 10),
            new Balance(new BalanceId(4, 1), prod4, loc1, 30),
            new Balance(new BalanceId(4, 3), prod4, loc3, 40),
            new Balance(new BalanceId(5, 1), prod5, loc1, 33),
            new Balance(new BalanceId(5, 2), prod5, loc2, 20)
    );

    @BeforeEach
    void setUp() {
        balanceService = Mockito.mock(BalanceService.class);
        balanceController = new BalanceController(balanceService);
    }

    @Test
    void getAvailableItems_filterByNothing() {
        Mockito.when(balanceService.getAllAvailableItems(0, "id", true)).thenReturn(
                new BalanceList(mockBalances.subList(0, 8), mockBalances.size()));

        BalanceList actual = balanceController.getAvailableItems(0, 0, 0, 0, "id", true);

        Mockito.verify(balanceService, Mockito.times(1)).getAllAvailableItems(0, "id", true);
        assertEquals(mockBalances.subList(0, 8), actual.getBalances());
        assertEquals(10, actual.getCount());
    }

    @Test
    void getAvailableItems_filterByLocationAndProduct() {
        Mockito.when(balanceService.getAvailableItemsByLocationAndProduct(3, 1, 0, "id", true)).thenReturn(
                new BalanceList(mockBalances.stream().filter(b -> b.getLocation().getId() == 3 && b.getProduct().getId() == 1).collect(Collectors.toList()), 1));

        BalanceList actual = balanceController.getAvailableItems(3, 1, 0, 0, "id", true);

        Mockito.verify(balanceService, Mockito.times(1)).getAvailableItemsByLocationAndProduct(3, 1, 0, "id", true);
        assertEquals(new Balance(new BalanceId(1, 3), prod1, loc3, 20), actual.getBalances().get(0));
        assertEquals(1, actual.getCount());
    }

    @Test
    void getAvailableItems_filterByLocationAndDept() {
        Mockito.when(balanceService.getAvailableItemsByLocationAndDept(1, 3, 0, "id", true)).thenReturn(
                new BalanceList(mockBalances.stream().filter(b -> b.getLocation().getId() == 1 && b.getProduct().getDept().getId() == 3).collect(Collectors.toList()), 2));

        BalanceList actual = balanceController.getAvailableItems(1, 0, 3, 0, "id", true);

        Mockito.verify(balanceService, Mockito.times(1)).getAvailableItemsByLocationAndDept(1, 3, 0, "id", true);
        assertEquals(new Balance(new BalanceId(4, 1), prod4, loc1, 30), actual.getBalances().get(0));
        assertEquals(2, actual.getCount());
    }

    @Test
    void getAvailableItems_filterByLocation() {
        Mockito.when(balanceService.getAvailableItemsByLocation(1, 0, "id", true)).thenReturn(
                new BalanceList(mockBalances.stream().filter(b -> b.getId().getLocationId() == 1).collect(Collectors.toList()), 5));

        BalanceList actual = balanceController.getAvailableItems(1, 0, 0, 0, "id", true);

        Mockito.verify(balanceService, Mockito.times(1)).getAvailableItemsByLocation(1, 0, "id", true);
        assertEquals(new Balance(new BalanceId(1, 1), prod1, loc1, 10), actual.getBalances().get(0));
        assertEquals(5, actual.getCount());
    }

    @Test
    void getAvailableItems_filterByProduct() {
        Mockito.when(balanceService.getAvailableItemsByProduct(1, 0, "id", true)).thenReturn(
                new BalanceList(mockBalances.stream().filter(b -> b.getId().getProductId() == 1).collect(Collectors.toList()), 3));

        BalanceList actual = balanceController.getAvailableItems(0, 1, 0, 0, "id", true);

        Mockito.verify(balanceService, Mockito.times(1)).getAvailableItemsByProduct(1, 0, "id", true);
        assertEquals(new Balance(new BalanceId(1, 1), prod1, loc1, 10), actual.getBalances().get(0));
        assertEquals(3, actual.getCount());
    }

    @Test
    void getAvailableItems_filterByDept() {
        Mockito.when(balanceService.getAvailableItemsByDept(1, 0, "id", true)).thenReturn(
                new BalanceList(mockBalances.stream().filter(b -> b.getProduct().getDept().getId() == 1).collect(Collectors.toList()), 4));

        BalanceList actual = balanceController.getAvailableItems(0, 0, 1, 0, "id", true);

        Mockito.verify(balanceService, Mockito.times(1)).getAvailableItemsByDept(1, 0, "id", true);
        assertEquals(new Balance(new BalanceId(1, 1), prod1, loc1, 10), actual.getBalances().get(0));
        assertEquals(4, actual.getCount());
    }
}