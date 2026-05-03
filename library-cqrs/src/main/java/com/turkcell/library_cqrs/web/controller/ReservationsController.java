package com.turkcell.library_cqrs.web.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turkcell.library_cqrs.application.features.category.command.create.CreateReservationCommand;
import com.turkcell.library_cqrs.application.features.category.query.getall.GetAllReservationsQuery;
import com.turkcell.library_cqrs.application.features.category.query.getall.ListReservationResponse;
import com.turkcell.library_cqrs.core.mediator.Mediator;

@RestController
@RequestMapping("/api/reservations")
public class ReservationsController {

    private final Mediator mediator;

    public ReservationsController(Mediator mediator) {
        this.mediator = mediator;
    }

    @PostMapping
    public UUID create(@RequestBody CreateReservationCommand command) {
        return mediator.send(command);
    }

    @GetMapping
    public List<ListReservationResponse> getAll() {
        return mediator.send(new GetAllReservationsQuery());
    }
}
