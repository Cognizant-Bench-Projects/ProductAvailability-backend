package com.cognizant.service;

import com.cognizant.model.*;
import com.cognizant.repository.BalanceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BalanceServiceTest {

    BalanceService balanceService;
    BalanceRepository balanceRepository;

    Department dept1 = new Department(1, "Shirts");
    Department dept2 = new Department(2, "Sweater");

    Product prod1 = new Product(1, "Long Sleeves", dept1);
    Product prod2 = new Product(2, "Short Sleeves", dept1);
    Product prod3 = new Product(3, "Crew Neck", dept2);

    Location loc1 = new Location(1, "Irving", "75063");
    Location loc2 = new Location(2, "New York", "10024");

    @BeforeEach
    void setUp() {
        balanceRepository = Mockito.mock(BalanceRepository.class);
        balanceService = new BalanceService(balanceRepository);
    }

    @Test
    void getAllAvailableItems() {
        List<Balance> expected = Arrays.asList(
                new Balance(new BalanceId(1, 1), prod1, loc1, 10),
                new Balance(new BalanceId(1, 2), prod1, loc2, 18),
                new Balance(new BalanceId(2, 1), prod2, loc1, 6),
                new Balance(new BalanceId(3, 1), prod3, loc1, 1)
        );

        Mockito.when(balanceRepository.findAll()).thenReturn(expected);

        List<Balance> actual = balanceService.getAllAvailableItems();

        Mockito.verify(balanceRepository).findAll();
        assertEquals(expected, actual);
    }

    @Test
    void getAvailableItemsByLocation() {
        List<Balance> expected = Arrays.asList(
                new Balance(new BalanceId(1, 1), prod1, loc1, 10),
                new Balance(new BalanceId(2, 1), prod2, loc1, 6),
                new Balance(new BalanceId(3, 1), prod3, loc1, 1)
        );

        ArgumentCaptor<Location> argument = ArgumentCaptor.forClass(Location.class);

        Mockito.when(balanceRepository.findByLocation(argument.capture())).thenReturn(expected);

        List<Balance> actual = balanceService.getAvailableItemsByLocation(1);

        Mockito.verify(balanceRepository).findByLocation(argument.capture());
        assertEquals(expected, actual);
    }

    @Test
    void getAvailableItemsByProduct() {
        List<Balance> expected = Arrays.asList(
                new Balance(new BalanceId(1, 1), prod1, loc1, 10),
                new Balance(new BalanceId(1, 2), prod1, loc2, 18)
        );

        ArgumentCaptor<Product> argument = ArgumentCaptor.forClass(Product.class);

        Mockito.when(balanceRepository.findByProduct(argument.capture())).thenReturn(expected);

        List<Balance> actual = balanceService.getAvailableItemsByProduct(1);

        Mockito.verify(balanceRepository).findByProduct(argument.capture());
        assertEquals(expected, actual);
    }

    @Test
    void getAvailableItemsByDept() {
        List<Product> products = Arrays.asList(
                prod1,
                prod2
        );
        List<Balance> expected = Arrays.asList(
                new Balance(new BalanceId(1, 1), prod1, loc1, 10),
                new Balance(new BalanceId(1, 2), prod1, loc2, 18),
                new Balance(new BalanceId(2, 1), prod2, loc1, 6)
        );

        Mockito.when(balanceRepository.findByProduct(products.get(0))).thenReturn(expected.subList(0, 2));
        Mockito.when(balanceRepository.findByProduct(products.get(1))).thenReturn(expected.subList(2, 3));

        List<Balance> actual = balanceService.getAvailableItemsByDept(products);

        Mockito.verify(balanceRepository).findByProduct(products.get(0));
        Mockito.verify(balanceRepository).findByProduct(products.get(1));
        assertEquals(expected, actual);
    }

    @Test
    void getAvailableItemsByLocationAndProduct() {
        List<Balance> expected = Arrays.asList(
                new Balance(new BalanceId(3, 1), prod3, loc1, 1)
        );

        ArgumentCaptor<Location> locArgument = ArgumentCaptor.forClass(Location.class);
        ArgumentCaptor<Product> prodArgument = ArgumentCaptor.forClass(Product.class);

        Mockito.when(balanceRepository.findByLocationAndProduct(locArgument.capture(), prodArgument.capture())).thenReturn(expected);

        List<Balance> actual = balanceService.getAvailableItemsByLocationAndProduct(1, 3);

        Mockito.verify(balanceRepository).findByLocationAndProduct(locArgument.capture(), prodArgument.capture());
        assertEquals(expected, actual);
    }

    @Test
    void getAvailableItemsByLocationAndDept() {
        List<Product> products = Arrays.asList(
                prod1,
                prod2
        );
        List<Balance> expected = Arrays.asList(
                new Balance(new BalanceId(1, 1), prod1, loc1, 10),
                new Balance(new BalanceId(2, 1), prod2, loc1, 6)
        );

        Mockito.when(balanceRepository.findByLocationAndProduct(Mockito.any(Location.class), Mockito.eq(products.get(0)))).thenReturn(expected.subList(0, 1));
        Mockito.when(balanceRepository.findByLocationAndProduct(Mockito.any(Location.class), Mockito.eq(products.get(1)))).thenReturn(expected.subList(1, 2));

        List<Balance> actual = balanceService.getAvailableItemsByLocationAndDept(1, products);

        Mockito.verify(balanceRepository).findByLocationAndProduct(Mockito.any(Location.class), Mockito.eq(products.get(0)));
        Mockito.verify(balanceRepository).findByLocationAndProduct(Mockito.any(Location.class), Mockito.eq(products.get(1)));
        assertEquals(expected, actual);
    }
}