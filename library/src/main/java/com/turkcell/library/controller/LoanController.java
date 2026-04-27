package com.turkcell.library.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.*;

import com.turkcell.library.dto.CreateLoanRequest;
import com.turkcell.library.dto.LoanResponse;
import com.turkcell.library.dto.ReturnLoanRequest;
import com.turkcell.library.service.LoanServiceImpl;

@RestController
@RequestMapping("/api/loans")
public class LoanController {

    private final LoanServiceImpl loanService;

    public LoanController(LoanServiceImpl loanService) {
        this.loanService = loanService;
    }

    @PostMapping
    public LoanResponse create(@RequestBody CreateLoanRequest request) {
        return loanService.create(request);
    }

    @GetMapping
    public List<LoanResponse> getAll() {
        return loanService.getAll();
    }

    @GetMapping("/{id}")
    public LoanResponse getById(@PathVariable UUID id) {
        return loanService.getById(id);
    }

    @PutMapping("/{id}/return")
    public LoanResponse returnBook(
            @PathVariable UUID id,
            @RequestBody ReturnLoanRequest request) {
        return loanService.returnBook(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        loanService.delete(id);
    }
}