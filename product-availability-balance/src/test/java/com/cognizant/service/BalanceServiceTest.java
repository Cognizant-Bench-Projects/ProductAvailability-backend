package com.cognizant.service;

import com.cognizant.model.*;
import com.cognizant.repository.BalanceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class BalanceServiceTest {

    BalanceService balanceService;
    BalanceRepository balanceRepository;

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
        balanceRepository = Mockito.mock(BalanceRepository.class);
        balanceService = new BalanceService(balanceRepository);
    }

    @Test
    void getAllAvailableItems() {
        Pageable paging = PageRequest.of(0, 8, Sort.by("id").ascending());

        Mockito.when(balanceRepository.findAllPageable(paging)).thenReturn(mockBalances.subList(0, 8));
        Mockito.when(balanceRepository.countNumberOfAllItems()).thenReturn(mockBalances.size());

        BalanceList actual = balanceService.getAllAvailableItems(0, "id", true);

        Mockito.verify(balanceRepository, Mockito.times(1)).findAllPageable(Mockito.any());
        Mockito.verify(balanceRepository, Mockito.times(1)).countNumberOfAllItems();
        assertEquals(mockBalances.subList(0, 8), actual.getBalances());
        assertEquals(mockBalances.size(), actual.getCount());
    }

    @Test
    void getAllAvailableItemsPageOne() {
        Pageable paging = PageRequest.of(1, 8, Sort.by("id").ascending());

        Mockito.when(balanceRepository.findAllPageable(paging)).thenReturn(mockBalances.subList(8, 10));
        Mockito.when(balanceRepository.countNumberOfAllItems()).thenReturn(mockBalances.size());

        BalanceList actual = balanceService.getAllAvailableItems(1, "id", true);

        Mockito.verify(balanceRepository, Mockito.times(1)).findAllPageable(Mockito.any());
        Mockito.verify(balanceRepository, Mockito.times(1)).countNumberOfAllItems();
        assertEquals(mockBalances.subList(8, 10), actual.getBalances());
        assertEquals(mockBalances.size(), actual.getCount());
    }

    @Test
    void getAvailableItemsByLocation() {
        Pageable paging = PageRequest.of(0, 8, Sort.by("id").ascending());

        Mockito.when(balanceRepository.findByLocationPageable(1, paging)).thenReturn(
                mockBalances.stream().filter(b -> b.getId().getLocationId() == 1).collect(Collectors.toList()));
        Mockito.when(balanceRepository.countNumberOfItemsByLocation(1)).thenReturn(3);

        BalanceList actual = balanceService.getAvailableItemsByLocation(1, 0, "id", true);

        Mockito.verify(balanceRepository, Mockito.times(1)).findByLocationPageable(1, paging);
        Mockito.verify(balanceRepository, Mockito.times(1)).countNumberOfItemsByLocation(1);
        assertEquals(3, actual.getCount());
        assertEquals(new BalanceId(1, 1), actual.getBalances().get(0).getId());
    }

    @Test
    void getAvailableItemsByProduct() {
        Mockito.when(balanceRepository.findByProduct(Mockito.any())).thenReturn(
                mockBalances.stream().filter(b -> b.getId().getProductId() == 5).collect(Collectors.toList()));

        BalanceList actual = balanceService.getAvailableItemsByProduct(5, -1, "id", true);

        Mockito.verify(balanceRepository, Mockito.times(1)).findByProduct(Mockito.any());
        Mockito.verify(balanceRepository, Mockito.times(0)).countNumberOfItemsByProduct(5);
        assertEquals(0, actual.getCount());
        assertEquals(new BalanceId(5, 1), actual.getBalances().get(0).getId());
    }

    @Test
    void getAvailableItemsByProductPageable() {
        Pageable paging = PageRequest.of(0, 8, Sort.by("id").ascending());

        Mockito.when(balanceRepository.findByProductPageable(5, paging)).thenReturn(
                mockBalances.stream().filter(b -> b.getId().getProductId() == 5).collect(Collectors.toList()));
        Mockito.when(balanceRepository.countNumberOfItemsByProduct(5)).thenReturn(2);;

        BalanceList actual = balanceService.getAvailableItemsByProduct(5, 0, "id", true);

        Mockito.verify(balanceRepository, Mockito.times(1)).findByProductPageable(5, paging);
        Mockito.verify(balanceRepository, Mockito.times(1)).countNumberOfItemsByProduct(5);
        assertEquals(2, actual.getCount());
        assertEquals(new BalanceId(5, 1), actual.getBalances().get(0).getId());
    }

    @Test
    void getAvailableItemsByDept() {
        Pageable paging = PageRequest.of(0, 8, Sort.by("id").ascending());

        Mockito.when(balanceRepository.findByDeptPageable(1, paging)).thenReturn(
                mockBalances.stream().filter(b -> b.getProduct().getDept().getId() == 1).collect(Collectors.toList()));
        Mockito.when(balanceRepository.countNumberOfItemsByDept(1)).thenReturn(4);

        BalanceList actual = balanceService.getAvailableItemsByDept(1, 0, "id", true);

        Mockito.verify(balanceRepository, Mockito.times(1)).findByDeptPageable(1, paging);
        Mockito.verify(balanceRepository, Mockito.times(1)).countNumberOfItemsByDept(1);
        assertEquals(4, actual.getCount());
        assertEquals(new BalanceId(2, 1), actual.getBalances().get(3).getId());
    }

    @Test
    void getAvailableItemsByLocationAndProduct() {
        Pageable paging = PageRequest.of(0, 8, Sort.by("id").ascending());

        Mockito.when(balanceRepository.findByProductAndLocationPageable(1, 3, paging)).thenReturn(
                mockBalances.stream().filter(b -> b.getLocation().getId() == 3 && b.getProduct().getId() == 1).collect(Collectors.toList()));
        Mockito.when(balanceRepository.countNumberOfItemsByProductAndLocation(1, 3)).thenReturn(1);

        BalanceList actual = balanceService.getAvailableItemsByLocationAndProduct(3, 1, 0, "id", true);

        Mockito.verify(balanceRepository, Mockito.times(1)).findByProductAndLocationPageable(1, 3, paging);
        Mockito.verify(balanceRepository, Mockito.times(1)).countNumberOfItemsByProductAndLocation(1, 3);
        assertEquals(1, actual.getCount());
        assertEquals(new BalanceId(1, 3), actual.getBalances().get(0).getId());
    }

    @Test
    void getAvailableItemsByLocationAndDept() {
        Pageable paging = PageRequest.of(0, 8, Sort.by("id").ascending());

        Mockito.when(balanceRepository.findByDeptAndLocationPageable(3, 1, paging)).thenReturn(
                mockBalances.stream().filter(b -> b.getLocation().getId() == 1 && b.getProduct().getDept().getId() == 3).collect(Collectors.toList()));
        Mockito.when(balanceRepository.countNumberOfItemsByDeptAndLocation(3, 1)).thenReturn(2);

        BalanceList actual = balanceService.getAvailableItemsByLocationAndDept(1, 3, 0, "id", true);

        Mockito.verify(balanceRepository, Mockito.times(1)).findByDeptAndLocationPageable(3, 1, paging);
        Mockito.verify(balanceRepository, Mockito.times(1)).countNumberOfItemsByDeptAndLocation(3, 1);
        assertEquals(2, actual.getCount());
        assertEquals(new BalanceId(5, 1), actual.getBalances().get(1).getId());
    }
}