package com.turkcell.library_cqrs.application.features.bookcopy.query;

import java.util.UUID;

public record ListBookCopyResponse(
        UUID id,
        UUID bookId,
        String bookTitle,
        String status,
        UUID locationId,
        String shelfNumber,
        Integer floor,
        String section) {
}
