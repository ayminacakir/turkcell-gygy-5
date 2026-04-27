package com.turkcell.library.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.*;

import com.turkcell.library.dto.CreateStudentRequest;
import com.turkcell.library.dto.StudentResponse;
import com.turkcell.library.dto.UpdateStudentRequest;
import com.turkcell.library.service.StudentServiceImpl;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentServiceImpl studentService;

    public StudentController(StudentServiceImpl studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public StudentResponse create(@RequestBody CreateStudentRequest request) {
        return studentService.create(request);
    }

    @GetMapping
    public List<StudentResponse> getAll() {
        return studentService.getAll();
    }

    @GetMapping("/{id}")
    public StudentResponse getById(@PathVariable UUID id) {
        return studentService.getById(id);
    }

    @PutMapping("/{id}")
    public StudentResponse update(
            @PathVariable UUID id,
            @RequestBody UpdateStudentRequest request) {
        return studentService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        studentService.delete(id);
    }
}