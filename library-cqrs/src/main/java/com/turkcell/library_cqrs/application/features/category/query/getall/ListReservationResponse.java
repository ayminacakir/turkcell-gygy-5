package com.turkcell.library_cqrs.application.features.category.query.getall;

import java.time.LocalDateTime;
import java.util.UUID;

public record ListReservationResponse(
        UUID id,
        UUID bookId,
        String bookTitle,
        UUID studentId,
        String studentFullName,
        LocalDateTime reservationDate,
        String status) {
}