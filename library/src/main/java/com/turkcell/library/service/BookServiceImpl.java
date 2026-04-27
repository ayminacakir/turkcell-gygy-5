package com.turkcell.library.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.turkcell.library.dto.BookResponse;
import com.turkcell.library.dto.CreateBookRequest;
import com.turkcell.library.dto.UpdateBookRequest;
import com.turkcell.library.entity.Author;
import com.turkcell.library.entity.Book;
import com.turkcell.library.entity.Category;
import com.turkcell.library.repository.AuthorRepository;
import com.turkcell.library.repository.BookRepository;
import com.turkcell.library.repository.CategoryRepository;

@Service
public class BookServiceImpl {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final CategoryRepository categoryRepository;

    public BookServiceImpl(
            BookRepository bookRepository,
            AuthorRepository authorRepository,
            CategoryRepository categoryRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.categoryRepository = categoryRepository;
    }

    public BookResponse create(CreateBookRequest request) {
        Book book = new Book();

        book.setIsbn(request.getIsbn());
        book.setTitle(request.getTitle());
        book.setPublisher(request.getPublisher());
        book.setPublicationYear(request.getPublicationYear());

        Set<Author> authors = new HashSet<>(
                authorRepository.findAllById(request.getAuthorIds()));

        Set<Category> categories = new HashSet<>(
                categoryRepository.findAllById(request.getCategoryIds()));

        book.setAuthors(authors);
        book.setCategories(categories);

        book = bookRepository.save(book);

        return mapToResponse(book);
    }

    public List<BookResponse> getAll() {
        List<Book> books = bookRepository.findAll();
        List<BookResponse> responseList = new ArrayList<>();

        for (Book book : books) {
            responseList.add(mapToResponse(book));
        }

        return responseList;
    }

    public BookResponse getById(UUID id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Kitap bulunamadı."));

        return mapToResponse(book);
    }

    public BookResponse update(UUID id, UpdateBookRequest request) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Kitap bulunamadı."));

        book.setIsbn(request.getIsbn());
        book.setTitle(request.getTitle());
        book.setPublisher(request.getPublisher());
        book.setPublicationYear(request.getPublicationYear());

        Set<Author> authors = new HashSet<>(
                authorRepository.findAllById(request.getAuthorIds()));

        Set<Category> categories = new HashSet<>(
                categoryRepository.findAllById(request.getCategoryIds()));

        book.setAuthors(authors);
        book.setCategories(categories);

        book = bookRepository.save(book);

        return mapToResponse(book);
    }

    public void delete(UUID id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Kitap bulunamadı."));

        bookRepository.delete(book);
    }

    private BookResponse mapToResponse(Book book) {
        BookResponse response = new BookResponse();

        response.setId(book.getId());
        response.setIsbn(book.getIsbn());
        response.setTitle(book.getTitle());
        response.setPublisher(book.getPublisher());
        response.setPublicationYear(book.getPublicationYear());

        response.setAuthors(
                book.getAuthors()
                        .stream()
                        .map(author -> author.getFirstname() + " " + author.getLastname())
                        .collect(Collectors.toList()));

        response.setCategories(
                book.getCategories()
                        .stream()
                        .map(Category::getCategoryName)
                        .collect(Collectors.toList()));

        return response;
    }
}
