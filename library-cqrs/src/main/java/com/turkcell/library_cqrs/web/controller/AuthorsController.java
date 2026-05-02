package com.turkcell.library_cqrs.web.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turkcell.library_cqrs.application.features.category.command.create.CreateAuthorCommand;
import com.turkcell.library_cqrs.application.features.category.query.getall.GetAllAuthorsQuery;
import com.turkcell.library_cqrs.application.features.category.query.getall.ListAuthorResponse;
import com.turkcell.library_cqrs.core.mediator.Mediator;

@RestController
@RequestMapping("/api/authors")
public class AuthorsController {

    private final Mediator mediator;

    public AuthorsController(Mediator mediator) {
        this.mediator = mediator;
    }

    @PostMapping
    public UUID create(@RequestBody CreateAuthorCommand command) {
        return mediator.send(command);
    }

    @GetMapping
    public List<ListAuthorResponse> getAll() {
        return mediator.send(new GetAllAuthorsQuery());
    }
}