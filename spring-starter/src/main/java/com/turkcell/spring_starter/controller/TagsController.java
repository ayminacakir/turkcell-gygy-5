package com.turkcell.spring_starter.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.*;

import com.turkcell.spring_starter.dto.CreateTagRequest;
import com.turkcell.spring_starter.dto.CreatedTagResponse;
import com.turkcell.spring_starter.dto.ListTagResponse;
import com.turkcell.spring_starter.dto.UpdateTagRequest;
import com.turkcell.spring_starter.service.TagServiceImpl;

@RestController
@RequestMapping("/api/tags")
public class TagsController {

    private final TagServiceImpl tagService;

    public TagsController(TagServiceImpl tagService) {
        this.tagService = tagService;
    }

    @PostMapping
    public CreatedTagResponse create(@RequestBody CreateTagRequest request) {
        return tagService.create(request);
    }

    @GetMapping
    public List<ListTagResponse> getAll() {
        return tagService.getAll();
    }

    @GetMapping("/{id}")
    public ListTagResponse getById(@PathVariable UUID id) {
        return tagService.getById(id);
    }

    @PutMapping("/{id}")
    public ListTagResponse update(
            @PathVariable UUID id,
            @RequestBody UpdateTagRequest request) {

        return tagService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        tagService.delete(id);
    }
}