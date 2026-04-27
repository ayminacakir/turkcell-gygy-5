package com.turkcell.library.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turkcell.library.dto.AuthorResponse;
import com.turkcell.library.dto.CreateAuthorRequest;
import com.turkcell.library.dto.UpdateAuthorRequest;
import com.turkcell.library.service.AuthorServiceImpl;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    private final AuthorServiceImpl authorService;

    public AuthorController(AuthorServiceImpl authorService) {
        this.authorService = authorService;
    }

    @PostMapping
    public AuthorResponse create(@RequestBody CreateAuthorRequest request) {
        return authorService.create(request);
    }

    @GetMapping
    public List<AuthorResponse> getAll() {
        return authorService.getAll();
    }

    @GetMapping("/{id}")
    public AuthorResponse getById(@PathVariable UUID id) {
        return authorService.getById(id);
    }

    @PutMapping("/{id}")
    public AuthorResponse update(
            @PathVariable UUID id,
            @RequestBody UpdateAuthorRequest request) {
        return authorService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        authorService.delete(id);
    }
}