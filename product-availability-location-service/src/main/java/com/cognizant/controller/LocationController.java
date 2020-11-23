package com.cognizant.controller;

import com.cognizant.model.Location;
import com.cognizant.service.LocationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/locations")
public class LocationController {

    private LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping
    public List<Location> getAllLocation() {
        return this.locationService.getAllLocation();
    }

    @GetMapping("/{locationId}")
    public Location getLocationById(@PathVariable int id) {
        return this.locationService.getLocationById(id);
    }
}
