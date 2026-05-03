package com.turkcell.library_cqrs.application.features.category.command.create;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.turkcell.library_cqrs.core.mediator.cqrs.CommandHandler;
import com.turkcell.library_cqrs.domain.entities.Author;
import com.turkcell.library_cqrs.domain.entities.Book;
import com.turkcell.library_cqrs.domain.entities.Category;
import com.turkcell.library_cqrs.persistence.repositories.AuthorRepository;
import com.turkcell.library_cqrs.persistence.repositories.BookRepository;
import com.turkcell.library_cqrs.persistence.repositories.CategoryRepository;

@Component
public class CreateBookCommandHandler implements CommandHandler<CreateBookCommand, UUID> {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final CategoryRepository categoryRepository;

    public CreateBookCommandHandler(
            BookRepository bookRepository,
            AuthorRepository authorRepository,
            CategoryRepository categoryRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    @Transactional
    public UUID handle(CreateBookCommand command) {

        if (bookRepository.existsByIsbn(command.isbn())) {
            throw new RuntimeException("Bu ISBN ile kayıtlı bir kitap zaten var.");
        }

        Set<Author> authors = new HashSet<>(authorRepository.findAllById(command.authorIds()));// Command içinden gelen
                                                                                               // author id’lerini al.
                                                                                               // Database’e git. Bu
                                                                                               // id’lere sahip Author
                                                                                               // kayıtlarını bul.
                                                                                               // Onları Set<Author>
                                                                                               // haline getir.
        Set<Category> categories = new HashSet<>(categoryRepository.findAllById(command.categoryIds()));

        if (authors.size() != command.authorIds().size()) {
            throw new RuntimeException("Gönderilen yazarlardan bazıları bulunamadı.");
        }

        if (categories.size() != command.categoryIds().size()) {
            throw new RuntimeException("Gönderilen kategorilerden bazıları bulunamadı.");
        }

        Book book = new Book();
        book.setIsbn(command.isbn());
        book.setTitle(command.title());
        book.setPublisher(command.publisher());
        book.setPublicationYear(command.publicationYear());
        book.setAuthors(authors);
        book.setCategories(categories);

        Book savedBook = bookRepository.save(book);

        return savedBook.getId();
    }
}
