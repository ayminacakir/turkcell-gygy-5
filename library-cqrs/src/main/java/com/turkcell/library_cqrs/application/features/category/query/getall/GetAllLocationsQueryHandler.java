package com.turkcell.library_cqrs.application.features.category.query.getall;

import java.util.List;

import org.springframework.stereotype.Component;

import com.turkcell.library_cqrs.core.mediator.cqrs.QueryHandler;
import com.turkcell.library_cqrs.persistence.repositories.LocationRepository;

@Component
public class GetAllLocationsQueryHandler implements QueryHandler<GetAllLocationsQuery, List<ListLocationResponse>> {

    private final LocationRepository locationRepository;

    public GetAllLocationsQueryHandler(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Override
    public List<ListLocationResponse> handle(GetAllLocationsQuery query) {

        return locationRepository.findAll()
                .stream()
                .map(location -> new ListLocationResponse(// her Location entity’sini response modeline çeviriyor.
                        location.getId(),
                        location.getShelfNumber(),
                        location.getFloor(),
                        location.getSection()))
                .toList();
    }
}