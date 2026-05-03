package com.turkcell.library_cqrs.application.features.category.query.getall;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.turkcell.library_cqrs.core.mediator.cqrs.QueryHandler;
import com.turkcell.library_cqrs.persistence.repositories.BookCopyRepository;

@Component
public class GetAllBookCopiesQueryHandler
        implements QueryHandler<GetAllBookCopiesQuery, List<ListBookCopyResponse>> {

    private final BookCopyRepository bookCopyRepository;

    public GetAllBookCopiesQueryHandler(BookCopyRepository bookCopyRepository) {
        this.bookCopyRepository = bookCopyRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ListBookCopyResponse> handle(GetAllBookCopiesQuery query) {

        return bookCopyRepository.findAll()
                .stream()
                .map(bookCopy -> new ListBookCopyResponse(
                        bookCopy.getId(),
                        bookCopy.getBook().getId(),
                        bookCopy.getBook().getTitle(),
                        bookCopy.getStatus(),
                        bookCopy.getLocation() != null ? bookCopy.getLocation().getId() : null,
                        bookCopy.getLocation() != null ? bookCopy.getLocation().getShelfNumber() : null,
                        bookCopy.getLocation() != null ? bookCopy.getLocation().getFloor() : null,
                        bookCopy.getLocation() != null ? bookCopy.getLocation().getSection() : null))
                .toList();
    }
}
