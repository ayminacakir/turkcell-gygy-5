package com.turkcell.library_cqrs.web.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turkcell.library_cqrs.application.features.fine.query.GetAllFinesQuery;
import com.turkcell.library_cqrs.application.features.fine.query.ListFineResponse;
import com.turkcell.library_cqrs.core.mediator.Mediator;

@RestController
@RequestMapping("/api/fines")
public class FinesController {

    private final Mediator mediator;

    public FinesController(Mediator mediator) {
        this.mediator = mediator;
    }

    @GetMapping
    public List<ListFineResponse> getAll() {
        return mediator.send(new GetAllFinesQuery());
    }
}