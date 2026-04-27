package com.turkcell.library.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.turkcell.library.dto.CreateLocationRequest;
import com.turkcell.library.dto.LocationResponse;
import com.turkcell.library.dto.UpdateLocationRequest;
import com.turkcell.library.entity.Location;
import com.turkcell.library.repository.LocationRepository;

@Service
public class LocationServiceImpl {

    private final LocationRepository locationRepository;

    public LocationServiceImpl(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public LocationResponse create(CreateLocationRequest request) {
        Location location = new Location();

        location.setShelfNumber(request.getShelfNumber());
        location.setFloor(request.getFloor());
        location.setSection(request.getSection());

        location = locationRepository.save(location);

        return mapToResponse(location);
    }

    public List<LocationResponse> getAll() {
        List<Location> locations = locationRepository.findAll();
        List<LocationResponse> responseList = new ArrayList<>();

        for (Location location : locations) {
            responseList.add(mapToResponse(location));
        }

        return responseList;
    }

    public LocationResponse getById(UUID id) {
        Location location = locationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Konum bulunamadı."));

        return mapToResponse(location);
    }

    public LocationResponse update(UUID id, UpdateLocationRequest request) {
        Location location = locationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Konum bulunamadı."));

        location.setShelfNumber(request.getShelfNumber());
        location.setFloor(request.getFloor());
        location.setSection(request.getSection());

        location = locationRepository.save(location);

        return mapToResponse(location);
    }

    public void delete(UUID id) {
        Location location = locationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Konum bulunamadı."));

        locationRepository.delete(location);
    }

    private LocationResponse mapToResponse(Location location) {
        LocationResponse response = new LocationResponse();

        response.setId(location.getId());
        response.setShelfNumber(location.getShelfNumber());
        response.setFloor(location.getFloor());
        response.setSection(location.getSection());

        return response;
    }
}
