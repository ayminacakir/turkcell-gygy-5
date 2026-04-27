package com.turkcell.library.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.*;

import com.turkcell.library.dto.BookResponse;
import com.turkcell.library.dto.CreateBookRequest;
import com.turkcell.library.dto.UpdateBookRequest;
import com.turkcell.library.service.BookServiceImpl;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookServiceImpl bookService;

    public BookController(BookServiceImpl bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public BookResponse create(@RequestBody CreateBookRequest request) {
        return bookService.create(request);
    }

    @GetMapping
    public List<BookResponse> getAll() {
        return bookService.getAll();
    }

    @GetMapping("/{id}")
    public BookResponse getById(@PathVariable UUID id) {
        return bookService.getById(id);
    }

    @PutMapping("/{id}")
    public BookResponse update(
            @PathVariable UUID id,
            @RequestBody UpdateBookRequest request) {
        return bookService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        bookService.delete(id);
    }
}