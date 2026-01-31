package com.project.erp.controller;

import com.project.erp.model.Location;
import com.project.erp.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("location")
public class LocationController {
    @Autowired
    LocationService locationService;

    @PreAuthorize("hasAnyRole('WAREHOUSE_MANAGER','ADMIN')")
    @PostMapping("post")
    public ResponseEntity<Location> addLocation(@RequestBody Location location){
        locationService.addLocation(location);
        Location savedLocation = locationService.getLocation(location.getLocation_id());
        if(savedLocation != null){
            URI uri = URI.create("/location/get/" + savedLocation.getLocation_id());
            return ResponseEntity.created(uri).body(savedLocation);
        }
        return ResponseEntity.badRequest().body(null);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<Location> getLocation(@PathVariable("id") UUID id){
        Location loc = locationService.getLocation(id);
        if(loc != null){
            return ResponseEntity.ok(loc);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("get")
    public ResponseEntity<List<Location>> getLocations(){
        return ResponseEntity.ok(locationService.getAllLocations());
    }
}
