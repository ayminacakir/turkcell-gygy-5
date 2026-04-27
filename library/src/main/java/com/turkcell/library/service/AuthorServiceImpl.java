package com.turkcell.library.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.turkcell.library.dto.AuthorResponse;
import com.turkcell.library.dto.CreateAuthorRequest;
import com.turkcell.library.dto.UpdateAuthorRequest;
import com.turkcell.library.entity.Author;
import com.turkcell.library.repository.AuthorRepository;

@Service
public class AuthorServiceImpl {

    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public AuthorResponse create(CreateAuthorRequest request) {
        Author author = new Author();

        author.setFirstname(request.getFirstName());
        author.setLastname(request.getLastName());
        author.setBiography(request.getBiography());

        author = authorRepository.save(author);

        return mapToResponse(author);
    }

    public List<AuthorResponse> getAll() {
        List<Author> authors = authorRepository.findAll();
        List<AuthorResponse> responseList = new ArrayList<>();

        for (Author author : authors) {
            responseList.add(mapToResponse(author));
        }

        return responseList;
    }

    public AuthorResponse getById(UUID id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Yazar bulunamadı."));

        return mapToResponse(author);
    }

    public AuthorResponse update(UUID id, UpdateAuthorRequest request) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Yazar bulunamadı."));

        author.setFirstname(request.getFirstName());
        author.setLastname(request.getLastName());
        author.setBiography(request.getBiography());

        author = authorRepository.save(author);

        return mapToResponse(author);
    }

    public void delete(UUID id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Yazar bulunamadı."));

        authorRepository.delete(author);
    }

    private AuthorResponse mapToResponse(Author author) {
        AuthorResponse response = new AuthorResponse();

        response.setId(author.getId());
        response.setFirstName(author.getFirstname());
        response.setLastName(author.getLastname());
        response.setBiography(author.getBiography());

        return response;
    }
}