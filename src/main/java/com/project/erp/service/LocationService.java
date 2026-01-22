package com.project.erp.service;

import com.project.erp.model.Location;
import com.project.erp.repository.LocationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class LocationService {
    @Autowired
    LocationRepo locationRepo;

    public void addLocation(Location location){
        locationRepo.save(location);
    }

    public Location getLocation(UUID location_id){
        Optional<Location> loc = locationRepo.findById(location_id);
        return loc.orElse(null);
    }

    public List<Location> getAllLocations(){
        return locationRepo.findAll();
    }
}
