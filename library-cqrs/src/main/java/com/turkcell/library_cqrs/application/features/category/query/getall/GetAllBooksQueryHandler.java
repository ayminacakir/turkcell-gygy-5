package com.turkcell.library_cqrs.application.features.category.query.getall;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.turkcell.library_cqrs.core.mediator.cqrs.QueryHandler;
import com.turkcell.library_cqrs.persistence.repositories.BookRepository;

@Component
public class GetAllBooksQueryHandler implements QueryHandler<GetAllBooksQuery, List<ListBookResponse>> {

    private final BookRepository bookRepository;

    public GetAllBooksQueryHandler(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ListBookResponse> handle(GetAllBooksQuery query) {

        return bookRepository.findAll()
                .stream()
                .map(book -> new ListBookResponse(
                        book.getId(),
                        book.getIsbn(),
                        book.getTitle(),
                        book.getPublisher(),
                        book.getPublicationYear(),
                        book.getAuthors()
                                .stream()
                                .map(author -> author.getFirstname() + " " + author.getLastname())
                                .toList(),
                        book.getCategories()
                                .stream()
                                .map(category -> category.getCategoryName())
                                .toList()))
                .toList();
    }
}
