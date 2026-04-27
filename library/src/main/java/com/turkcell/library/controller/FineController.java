package com.turkcell.library.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.*;

import com.turkcell.library.dto.FineResponse;
import com.turkcell.library.service.FineServiceImpl;

@RestController
@RequestMapping("/api/fines")
public class FineController {

    private final FineServiceImpl fineService;

    public FineController(FineServiceImpl fineService) {
        this.fineService = fineService;
    }

    @GetMapping
    public List<FineResponse> getAll() {
        return fineService.getAll();
    }

    @GetMapping("/{id}")
    public FineResponse getById(@PathVariable UUID id) {
        return fineService.getById(id);
    }

    @PutMapping("/{id}/pay")
    public FineResponse markAsPaid(@PathVariable UUID id) {
        return fineService.markAsPaid(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        fineService.delete(id);
    }
}