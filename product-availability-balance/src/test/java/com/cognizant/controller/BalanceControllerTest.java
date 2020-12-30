//package com.cognizant.controller;
//
//import com.cognizant.model.*;
//import com.cognizant.service.BalanceService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//
//import java.util.Arrays;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class BalanceControllerTest {
//
//    BalanceController balanceController;
//    BalanceService balanceService;
//
//    Department dept1 = new Department(1, "Shirts");
//    Department dept2 = new Department(2, "Sweater");
//
//    Product prod1 = new Product(1, "Long Sleeves", dept1);
//    Product prod2 = new Product(2, "Short Sleeves", dept1);
//    Product prod3 = new Product(3, "Crew Neck", dept2);
//
//    Location loc1 = new Location(1, "Irving", "75063");
//    Location loc2 = new Location(2, "New York", "10024");
//
//    @BeforeEach
//    void setUp() {
//        balanceService = Mockito.mock(BalanceService.class);
//        balanceController = new BalanceController(balanceService);
//    }
//
//    @Test
//    void getAvailableItems_filterByNothing() {
//        List<Balance> expected = Arrays.asList(
//                new Balance(new BalanceId(1, 1), prod1, loc1, 10),
//                new Balance(new BalanceId(1, 2), prod1, loc2, 18),
//                new Balance(new BalanceId(2, 1), prod2, loc1, 6),
//                new Balance(new BalanceId(3, 1), prod3, loc1, 1)
//        );
//
//        Mockito.when(balanceService.getAllAvailableItems()).thenReturn(expected);
//
//        List<Balance> actual = balanceController.getAvailableItems("0", "0", "0");
//
//        Mockito.verify(balanceService).getAllAvailableItems();
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    void getAvailableItems_filterByLocationAndProduct() {
//        List<Balance> expected = Arrays.asList(
//                new Balance(new BalanceId(1, 1), prod1, loc1, 10)
//        );
//
//        Mockito.when(balanceService.getAvailableItemsByLocationAndProduct(1, 1)).thenReturn(expected);
//
//        List<Balance> actual = balanceController.getAvailableItems("1", "1", "0");
//
//        Mockito.verify(balanceService).getAvailableItemsByLocationAndProduct(1, 1);
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    void getAvailableItems_filterByLocationAndDept() {
//        List<Product> products = Arrays.asList(
//                prod1,
//                prod2
//        );
//        List<Balance> expected = Arrays.asList(
//                new Balance(new BalanceId(1, 1), prod1, loc1, 10),
//                new Balance(new BalanceId(2, 1), prod2, loc1, 6)
//        );
//
//        Mockito.when(productServiceProxy.getProductsByDept(1)).thenReturn(products);
//        Mockito.when(balanceService.getAvailableItemsByLocationAndDept(1, products)).thenReturn(expected);
//
//        List<Balance> actual = balanceController.getAvailableItems("1", "0", "1");
//
//        Mockito.verify(productServiceProxy).getProductsByDept(1);
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    void getAvailableItems_filterByLocation() {
//        List<Balance> expected = Arrays.asList(
//                new Balance(new BalanceId(1, 1), prod1, loc1, 10),
//                new Balance(new BalanceId(2, 1), prod2, loc1, 6),
//                new Balance(new BalanceId(3, 1), prod3, loc1, 1)
//        );
//
//        Mockito.when(balanceService.getAvailableItemsByLocation(1)).thenReturn(expected);
//
//        List<Balance> actual = balanceController.getAvailableItems("1", "0", "0");
//
//        Mockito.verify(balanceService).getAvailableItemsByLocation(1);
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    void getAvailableItems_filterByProduct() {
//        List<Balance> expected = Arrays.asList(
//                new Balance(new BalanceId(1, 1), prod1, loc1, 10),
//                new Balance(new BalanceId(1, 2), prod1, loc2, 18)
//        );
//
//        Mockito.when(balanceService.getAvailableItemsByProduct(1)).thenReturn(expected);
//
//        List<Balance> actual = balanceController.getAvailableItems("0", "1", "0");
//
//        Mockito.verify(balanceService).getAvailableItemsByProduct(1);
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    void getAvailableItems_filterByDept() {
//        List<Product> products = Arrays.asList(
//                prod1,
//                prod2
//        );
//        List<Balance> expected = Arrays.asList(
//                new Balance(new BalanceId(1, 1), prod1, loc1, 10),
//                new Balance(new BalanceId(1, 2), prod1, loc2, 18),
//                new Balance(new BalanceId(2, 1), prod2, loc1, 6)
//        );
//
//        Mockito.when(productServiceProxy.getProductsByDept(1)).thenReturn(products);
//        Mockito.when(balanceService.getAvailableItemsByDept(products)).thenReturn(expected);
//
//        List<Balance> actual = balanceController.getAvailableItems("0", "0", "1");
//
//        Mockito.verify(productServiceProxy).getProductsByDept(1);
//        assertEquals(expected, actual);
//    }
//}