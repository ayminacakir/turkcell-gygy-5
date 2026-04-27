package com.turkcell.library.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.*;

import com.turkcell.library.dto.CreateStaffRequest;
import com.turkcell.library.dto.StaffResponse;
import com.turkcell.library.dto.UpdateStaffRequest;
import com.turkcell.library.service.StaffServiceImpl;

@RestController
@RequestMapping("/api/staff")
public class StaffController {

    private final StaffServiceImpl staffService;

    public StaffController(StaffServiceImpl staffService) {
        this.staffService = staffService;
    }

    @PostMapping
    public StaffResponse create(@RequestBody CreateStaffRequest request) {
        return staffService.create(request);
    }

    @GetMapping
    public List<StaffResponse> getAll() {
        return staffService.getAll();
    }

    @GetMapping("/{id}")
    public StaffResponse getById(@PathVariable UUID id) {
        return staffService.getById(id);
    }

    @PutMapping("/{id}")
    public StaffResponse update(@PathVariable UUID id, @RequestBody UpdateStaffRequest request) {
        return staffService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        staffService.delete(id);
    }
}
