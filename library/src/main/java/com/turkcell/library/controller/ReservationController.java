package com.turkcell.library.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.*;

import com.turkcell.library.dto.CreateReservationRequest;
import com.turkcell.library.dto.ReservationResponse;
import com.turkcell.library.dto.UpdateReservationRequest;
import com.turkcell.library.service.ReservationServiceImpl;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    private final ReservationServiceImpl reservationService;

    public ReservationController(ReservationServiceImpl reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping
    public ReservationResponse create(@RequestBody CreateReservationRequest request) {
        return reservationService.create(request);
    }

    @GetMapping
    public List<ReservationResponse> getAll() {
        return reservationService.getAll();
    }

    @GetMapping("/{id}")
    public ReservationResponse getById(@PathVariable UUID id) {
        return reservationService.getById(id);
    }

    @PutMapping("/{id}")
    public ReservationResponse update(
            @PathVariable UUID id,
            @RequestBody UpdateReservationRequest request) {
        return reservationService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        reservationService.delete(id);
    }
}