package com.turkcell.library_cqrs.web.controller;

import java.util.UUID;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turkcell.library_cqrs.application.features.category.command.create.BorrowBookCommand;
import com.turkcell.library_cqrs.application.features.category.command.create.ReturnBookCommand;
import com.turkcell.library_cqrs.core.mediator.Mediator;

@RestController
@RequestMapping("/api/loans")
public class LoansController {

    private final Mediator mediator;

    public LoansController(Mediator mediator) {
        this.mediator = mediator;
    }

    @PostMapping("/borrow")
    public UUID borrowBook(@RequestBody BorrowBookCommand command) {
        return mediator.send(command);
    }

    @PostMapping("/return")
    public UUID returnBook(@RequestBody ReturnBookCommand command) {
        return mediator.send(command);
    }
}
