package com.turkcell.library.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.*;

import com.turkcell.library.dto.BookCopyResponse;
import com.turkcell.library.dto.CreateBookCopyRequest;
import com.turkcell.library.dto.UpdateBookCopyRequest;
import com.turkcell.library.service.BookCopyServiceImpl;

@RestController
@RequestMapping("/api/book-copies")
public class BookCopyController {

    private final BookCopyServiceImpl bookCopyService;

    public BookCopyController(BookCopyServiceImpl bookCopyService) {
        this.bookCopyService = bookCopyService;
    }

    @PostMapping
    public BookCopyResponse create(@RequestBody CreateBookCopyRequest request) {
        return bookCopyService.create(request);
    }

    @GetMapping
    public List<BookCopyResponse> getAll() {
        return bookCopyService.getAll();
    }

    @GetMapping("/{id}")
    public BookCopyResponse getById(@PathVariable UUID id) {
        return bookCopyService.getById(id);
    }

    @PutMapping("/{id}")
    public BookCopyResponse update(
            @PathVariable UUID id,
            @RequestBody UpdateBookCopyRequest request) {
        return bookCopyService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        bookCopyService.delete(id);
    }
}