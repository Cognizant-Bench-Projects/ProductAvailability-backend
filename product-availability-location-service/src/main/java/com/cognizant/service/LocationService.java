package com.cognizant.service;

import com.cognizant.model.Location;
import com.cognizant.repository.LocationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService {

    private LocationRepository locationRepository;

    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public List<Location> getAllLocation() {
        return this.locationRepository.findAll();
    }

    public Location getLocationById(int id) {
        return this.locationRepository.getOne(id);
    }
}
