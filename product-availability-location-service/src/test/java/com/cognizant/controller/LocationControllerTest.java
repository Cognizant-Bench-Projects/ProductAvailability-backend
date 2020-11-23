package com.cognizant.controller;

import com.cognizant.model.Location;
import com.cognizant.service.LocationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LocationControllerTest {

    LocationController locationController;
    LocationService locationService;

    @BeforeEach
    public void setup() {
        locationService = Mockito.mock(LocationService.class);
        locationController = new LocationController(locationService);
    }

    @Test
    void getAllLocation() {
        List<Location> expected = Arrays.asList(
                new Location(1, "Irving", "75063"),
                new Location(2, "New York", "10024")
        );

        Mockito.when(locationService.getAllLocation()).thenReturn(expected);

        List<Location> actual = locationController.getAllLocation();

        Mockito.verify(locationService).getAllLocation();
        assertEquals(expected, actual);
    }

    @Test
    void getLocationById() {
        Location expected = new Location(1, "Irving", "75063");

        Mockito.when(locationService.getLocationById(1)).thenReturn(expected);

        Location actual = locationController.getLocationById(1);

        Mockito.verify(locationService).getLocationById(1);
        assertEquals(expected, actual);
    }
}