package com.turkcell.library_cqrs.application.features.loan.query;

import java.time.LocalDate;
import java.util.UUID;

public record ListLoanResponse(
                UUID id,
                UUID bookCopyId,
                String bookTitle,
                UUID studentId,
                String studentFullName,
                UUID staffId,
                String staffFullName,
                LocalDate dueDate,
                LocalDate returnDate,
                String status) {
}