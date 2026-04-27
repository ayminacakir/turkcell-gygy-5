package com.turkcell.library.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.*;

import com.turkcell.library.dto.CreateLocationRequest;
import com.turkcell.library.dto.LocationResponse;
import com.turkcell.library.dto.UpdateLocationRequest;
import com.turkcell.library.service.LocationServiceImpl;

@RestController
@RequestMapping("/api/locations")
public class LocationController {

    private final LocationServiceImpl locationService;

    public LocationController(LocationServiceImpl locationService) {
        this.locationService = locationService;
    }

    @PostMapping
    public LocationResponse create(@RequestBody CreateLocationRequest request) {
        return locationService.create(request);
    }

    @GetMapping
    public List<LocationResponse> getAll() {
        return locationService.getAll();
    }

    @GetMapping("/{id}")
    public LocationResponse getById(@PathVariable UUID id) {
        return locationService.getById(id);
    }

    @PutMapping("/{id}")
    public LocationResponse update(@PathVariable UUID id, @RequestBody UpdateLocationRequest request) {
        return locationService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        locationService.delete(id);
    }
}
