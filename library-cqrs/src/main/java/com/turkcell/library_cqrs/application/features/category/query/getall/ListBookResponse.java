package com.turkcell.library_cqrs.application.features.category.query.getall;

import java.util.List;
import java.util.UUID;

public record ListBookResponse(
        UUID id,
        String isbn,
        String title,
        String publisher,
        Integer publicationYear,
        List<String> authors,
        List<String> categories) {
}