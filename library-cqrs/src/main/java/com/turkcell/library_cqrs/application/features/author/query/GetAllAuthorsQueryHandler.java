package com.turkcell.library_cqrs.application.features.author.query;

import com.turkcell.library_cqrs.application.features.author.mapper.AuthorMapper;
import java.util.List;

import org.springframework.stereotype.Component;

import com.turkcell.library_cqrs.core.mediator.cqrs.QueryHandler;
import com.turkcell.library_cqrs.persistence.repositories.AuthorRepository;
import com.turkcell.library_cqrs.domain.entities.Author;

@Component
public class GetAllAuthorsQueryHandler implements QueryHandler<GetAllAuthorsQuery, List<ListAuthorResponse>> {
    // GetAllAuthorsQueryHandler ne demek? Bu sınıf, GetAllAuthorsQuery adlı bir
    // sorguyu işlemekle sorumludur. Sorgu işleyicisi (QueryHandler) olarak
    // adlandırılır ve belirli bir sorgu türünü (GetAllAuthorsQuery) alır ve belirli
    // bir sonuç türü (List<ListAuthorResponse>) döndürür.

    private final AuthorMapper authorMapper;
    private final AuthorRepository authorRepository;

    public GetAllAuthorsQueryHandler(AuthorRepository authorRepository, AuthorMapper authorMapper) {
        this.authorRepository = authorRepository;
        this.authorMapper = authorMapper;
    }

    @Override
    public List<ListAuthorResponse> handle(GetAllAuthorsQuery query) {

        return authorRepository.findAll()
                .stream()
                .map(author -> authorMapper.listAuthorResponseFromAuthor(author))
                .toList();
    }
}