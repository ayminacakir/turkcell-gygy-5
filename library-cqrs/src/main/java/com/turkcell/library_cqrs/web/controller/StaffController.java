package com.turkcell.library_cqrs.web.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turkcell.library_cqrs.application.features.staff.command.CreateStaffCommand;
import com.turkcell.library_cqrs.application.features.staff.query.GetAllStaffQuery;
import com.turkcell.library_cqrs.application.features.staff.query.ListStaffResponse;
import com.turkcell.library_cqrs.core.mediator.Mediator;

@RestController
@RequestMapping("/api/staff")
public class StaffController {

    private final Mediator mediator;

    public StaffController(Mediator mediator) {
        this.mediator = mediator;
    }

    @PostMapping
    public UUID create(@RequestBody CreateStaffCommand command) {
        return mediator.send(command);
    }

    @GetMapping
    public List<ListStaffResponse> getAll() {
        return mediator.send(new GetAllStaffQuery());
    }
}
