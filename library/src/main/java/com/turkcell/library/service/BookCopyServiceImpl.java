package com.turkcell.library.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.turkcell.library.dto.BookCopyResponse;
import com.turkcell.library.dto.CreateBookCopyRequest;
import com.turkcell.library.dto.UpdateBookCopyRequest;
import com.turkcell.library.entity.Book;
import com.turkcell.library.entity.BookCopy;
import com.turkcell.library.entity.Location;
import com.turkcell.library.repository.BookCopyRepository;
import com.turkcell.library.repository.BookRepository;
import com.turkcell.library.repository.LocationRepository;

@Service
public class BookCopyServiceImpl {

    private final BookCopyRepository bookCopyRepository;
    private final BookRepository bookRepository;
    private final LocationRepository locationRepository;

    public BookCopyServiceImpl(
            BookCopyRepository bookCopyRepository,
            BookRepository bookRepository,
            LocationRepository locationRepository) {
        this.bookCopyRepository = bookCopyRepository;
        this.bookRepository = bookRepository;
        this.locationRepository = locationRepository;
    }

    public BookCopyResponse create(CreateBookCopyRequest request) {
        Book book = bookRepository.findById(request.getBookId())
                .orElseThrow(() -> new RuntimeException("Kitap bulunamadı."));

        Location location = locationRepository.findById(request.getLocationId())
                .orElseThrow(() -> new RuntimeException("Konum bulunamadı."));

        BookCopy bookCopy = new BookCopy();
        bookCopy.setBook(book);
        bookCopy.setLocation(location);
        bookCopy.setStatus(request.getStatus());

        bookCopy = bookCopyRepository.save(bookCopy);

        return mapToResponse(bookCopy);
    }

    public List<BookCopyResponse> getAll() {
        List<BookCopy> bookCopies = bookCopyRepository.findAll();
        List<BookCopyResponse> responseList = new ArrayList<>();

        for (BookCopy bookCopy : bookCopies) {
            responseList.add(mapToResponse(bookCopy));
        }

        return responseList;
    }

    public BookCopyResponse getById(UUID id) {
        BookCopy bookCopy = bookCopyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Kitap kopyası bulunamadı."));

        return mapToResponse(bookCopy);
    }

    public BookCopyResponse update(UUID id, UpdateBookCopyRequest request) {
        BookCopy bookCopy = bookCopyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Kitap kopyası bulunamadı."));

        Book book = bookRepository.findById(request.getBookId())
                .orElseThrow(() -> new RuntimeException("Kitap bulunamadı."));

        Location location = locationRepository.findById(request.getLocationId())
                .orElseThrow(() -> new RuntimeException("Konum bulunamadı."));

        bookCopy.setBook(book);
        bookCopy.setLocation(location);
        bookCopy.setStatus(request.getStatus());

        bookCopy = bookCopyRepository.save(bookCopy);

        return mapToResponse(bookCopy);
    }

    public void delete(UUID id) {
        BookCopy bookCopy = bookCopyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Kitap kopyası bulunamadı."));

        bookCopyRepository.delete(bookCopy);
    }

    private BookCopyResponse mapToResponse(BookCopy bookCopy) {
        BookCopyResponse response = new BookCopyResponse();

        response.setId(bookCopy.getId());
        response.setBookTitle(bookCopy.getBook().getTitle());
        response.setStatus(bookCopy.getStatus());

        Location location = bookCopy.getLocation();

        if (location != null) {
            response.setLocationInfo(
                    location.getSection() + " / Raf: " + location.getShelfNumber() + " / Kat: " + location.getFloor());
        }

        return response;
    }
}
