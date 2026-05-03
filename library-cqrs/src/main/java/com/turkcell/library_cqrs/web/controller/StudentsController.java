package com.turkcell.library_cqrs.web.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turkcell.library_cqrs.application.features.category.command.create.CreateStudentCommand;
import com.turkcell.library_cqrs.application.features.category.query.getall.GetAllStudentsQuery;
import com.turkcell.library_cqrs.application.features.category.query.getall.ListStudentResponse;
import com.turkcell.library_cqrs.core.mediator.Mediator;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/students")
public class StudentsController {
    private final Mediator mediator;

    public StudentsController(Mediator mediator) {
        this.mediator = mediator;
    }

    @PostMapping()
    public UUID create(@RequestBody CreateStudentCommand command) {
        return mediator.send(command);
    }

    @GetMapping
    public List<ListStudentResponse> getAll() {
        return mediator.send(new GetAllStudentsQuery());
    }

}
