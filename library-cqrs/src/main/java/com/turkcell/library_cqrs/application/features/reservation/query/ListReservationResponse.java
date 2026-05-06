package com.turkcell.library_cqrs.application.features.reservation.query;

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