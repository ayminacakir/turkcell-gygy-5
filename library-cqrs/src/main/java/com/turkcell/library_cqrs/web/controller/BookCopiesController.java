package com.turkcell.library_cqrs.web.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turkcell.library_cqrs.application.features.category.command.create.CreateBookCopyCommand;
import com.turkcell.library_cqrs.application.features.category.query.getall.GetAllBookCopiesQuery;
import com.turkcell.library_cqrs.application.features.category.query.getall.ListBookCopyResponse;
import com.turkcell.library_cqrs.core.mediator.Mediator;

@RestController
@RequestMapping("/api/book-copies")
public class BookCopiesController {

    private final Mediator mediator;

    public BookCopiesController(Mediator mediator) {
        this.mediator = mediator;
    }

    @PostMapping
    public UUID create(@RequestBody CreateBookCopyCommand command) {
        return mediator.send(command);
    }

    @GetMapping
    public List<ListBookCopyResponse> getAll() {
        return mediator.send(new GetAllBookCopiesQuery());
    }
}