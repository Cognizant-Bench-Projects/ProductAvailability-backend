package com.cognizant.service;

import com.cognizant.model.Location;
import com.cognizant.repository.LocationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LocationServiceTest {

    LocationService locationService;
    LocationRepository locationRepository;

    @BeforeEach
    public void setup() {
        locationRepository = Mockito.mock(LocationRepository.class);
        locationService = new LocationService(locationRepository);
    }

    @Test
    void getAllLocation() {
        List<Location> expected = Arrays.asList(
                new Location(1, "Irving", "75063"),
                new Location(2, "New York", "10024")
        );

        Mockito.when(locationRepository.findAll()).thenReturn(expected);

        List<Location> actual = locationService.getAllLocation();

        Mockito.verify(locationRepository).findAll();
        assertEquals(expected, actual);
    }

    @Test
    void getLocationById() {
        Location expected = new Location(1, "Irving", "75063");

        Mockito.when(locationRepository.getOne(1)).thenReturn(expected);

        Location actual = locationService.getLocationById(1);

        Mockito.verify(locationRepository).getOne(1);
        assertEquals(expected, actual);
    }
}