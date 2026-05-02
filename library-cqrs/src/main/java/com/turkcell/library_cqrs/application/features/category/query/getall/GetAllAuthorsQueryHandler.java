package com.turkcell.library_cqrs.application.features.category.query.getall;

import java.util.List;

import org.springframework.stereotype.Component;

import com.turkcell.library_cqrs.core.mediator.cqrs.QueryHandler;
import com.turkcell.library_cqrs.persistence.repositories.AuthorRepository;

@Component
public class GetAllAuthorsQueryHandler implements QueryHandler<GetAllAuthorsQuery, List<ListAuthorResponse>> {
    // GetAllAuthorsQueryHandler ne demek? Bu sınıf, GetAllAuthorsQuery adlı bir
    // sorguyu işlemekle sorumludur. Sorgu işleyicisi (QueryHandler) olarak
    // adlandırılır ve belirli bir sorgu türünü (GetAllAuthorsQuery) alır ve belirli
    // bir sonuç türü (List<ListAuthorResponse>) döndürür.

    private final AuthorRepository authorRepository;

    public GetAllAuthorsQueryHandler(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<ListAuthorResponse> handle(GetAllAuthorsQuery query) {

        return authorRepository.findAll()
                .stream()
                .map(author -> new ListAuthorResponse(
                        author.getId(),
                        author.getFirstname(),
                        author.getLastname(),
                        author.getBiography()))
                .toList();
    }
}