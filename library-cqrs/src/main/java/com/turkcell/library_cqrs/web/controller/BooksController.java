package com.turkcell.library_cqrs.web.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turkcell.library_cqrs.application.features.category.command.create.CreateBookCommand;
import com.turkcell.library_cqrs.application.features.category.query.getall.GetAllBooksQuery;
import com.turkcell.library_cqrs.application.features.category.query.getall.ListBookResponse;
import com.turkcell.library_cqrs.core.mediator.Mediator;

@RestController
@RequestMapping("/api/books")
public class BooksController {

    private final Mediator mediator;

    public BooksController(Mediator mediator) {
        this.mediator = mediator;
    }

    @PostMapping
    public UUID create(@RequestBody CreateBookCommand command) {
        return mediator.send(command);
    }

    @GetMapping
    public List<ListBookResponse> getAll() {
        return mediator.send(new GetAllBooksQuery());
    }
}
