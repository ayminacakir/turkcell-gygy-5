package com.turkcell.spring_starter.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.turkcell.spring_starter.dto.CreateTagRequest;
import com.turkcell.spring_starter.dto.CreatedTagResponse;
import com.turkcell.spring_starter.dto.ListTagResponse;
import com.turkcell.spring_starter.dto.UpdateTagRequest;
import com.turkcell.spring_starter.entity.Tag;
import com.turkcell.spring_starter.repository.TagRepository;

@Service
public class TagServiceImpl {

    private final TagRepository tagRepository;

    public TagServiceImpl(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public CreatedTagResponse create(CreateTagRequest request) {

        Tag tag = new Tag();
        tag.setName(request.getName());

        tag = tagRepository.save(tag);

        CreatedTagResponse response = new CreatedTagResponse();
        response.setId(tag.getId());
        response.setName(tag.getName());

        return response;
    }

    public List<ListTagResponse> getAll() {

        List<Tag> tags = tagRepository.findAll();

        List<ListTagResponse> responseList = new ArrayList<>();

        for (Tag tag : tags) {
            ListTagResponse response = new ListTagResponse();
            response.setId(tag.getId());
            response.setName(tag.getName());

            responseList.add(response);
        }

        return responseList;
    }

    public ListTagResponse getById(UUID id) {

        Tag tag = tagRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tag bulunamadı."));

        ListTagResponse response = new ListTagResponse();
        response.setId(tag.getId());
        response.setName(tag.getName());

        return response;
    }

    public ListTagResponse update(UUID id, UpdateTagRequest request) {

        Tag tag = tagRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tag bulunamadı."));

        tag.setName(request.getName());

        tag = tagRepository.save(tag);

        ListTagResponse response = new ListTagResponse();
        response.setId(tag.getId());
        response.setName(tag.getName());

        return response;
    }

    public void delete(UUID id) {

        Tag tag = tagRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tag bulunamadı."));

        tagRepository.delete(tag);
    }
}