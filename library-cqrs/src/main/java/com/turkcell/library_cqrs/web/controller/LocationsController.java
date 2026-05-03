package com.turkcell.library_cqrs.web.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turkcell.library_cqrs.application.features.category.command.create.CreateLocationCommand;
import com.turkcell.library_cqrs.application.features.category.query.getall.GetAllLocationsQuery;
import com.turkcell.library_cqrs.application.features.category.query.getall.ListLocationResponse;
import com.turkcell.library_cqrs.core.mediator.Mediator;

@RestController
@RequestMapping("/api/locations")
public class LocationsController {

    private final Mediator mediator;

    public LocationsController(Mediator mediator) {
        this.mediator = mediator;
    }

    @PostMapping
    public UUID create(@RequestBody CreateLocationCommand command) {
        return mediator.send(command);
    }

    @GetMapping
    public List<ListLocationResponse> getAll() {
        return mediator.send(new GetAllLocationsQuery());
    }
}