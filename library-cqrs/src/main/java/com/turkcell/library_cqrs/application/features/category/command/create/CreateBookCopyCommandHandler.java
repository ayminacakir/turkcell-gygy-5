package com.turkcell.library_cqrs.application.features.category.command.create;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.turkcell.library_cqrs.core.mediator.cqrs.CommandHandler;
import com.turkcell.library_cqrs.domain.entities.Book;
import com.turkcell.library_cqrs.domain.entities.BookCopy;
import com.turkcell.library_cqrs.domain.entities.Location;
import com.turkcell.library_cqrs.persistence.repositories.BookCopyRepository;
import com.turkcell.library_cqrs.persistence.repositories.BookRepository;
import com.turkcell.library_cqrs.persistence.repositories.LocationRepository;

@Component
public class CreateBookCopyCommandHandler implements CommandHandler<CreateBookCopyCommand, UUID> {

    private final BookCopyRepository bookCopyRepository;
    private final BookRepository bookRepository;
    private final LocationRepository locationRepository;

    public CreateBookCopyCommandHandler(
            BookCopyRepository bookCopyRepository,
            BookRepository bookRepository,
            LocationRepository locationRepository) {
        this.bookCopyRepository = bookCopyRepository;
        this.bookRepository = bookRepository;
        this.locationRepository = locationRepository;
    }

    @Override
    public UUID handle(CreateBookCopyCommand command) {

        Book book = bookRepository.findById(command.bookId())
                .orElseThrow(() -> new RuntimeException("Kitap bulunamadı."));

        Location location = locationRepository.findById(command.locationId())
                .orElseThrow(() -> new RuntimeException("Konum bulunamadı."));

        BookCopy bookCopy = new BookCopy();
        bookCopy.setBook(book);
        bookCopy.setLocation(location);

        if (command.status() == null || command.status().isBlank()) {
            bookCopy.setStatus("AVAILABLE");
        } else {
            bookCopy.setStatus(command.status());
        }

        BookCopy savedBookCopy = bookCopyRepository.save(bookCopy);

        return savedBookCopy.getId();
    }
}