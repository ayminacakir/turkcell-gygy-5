package com.turkcell.library_cqrs.application.features.category.query.getall;

import java.math.BigDecimal;
import java.util.UUID;

public record ListFineResponse(
        UUID id,
        UUID loanId,
        String studentFullName,
        String bookTitle,
        BigDecimal amount,
        Boolean isPaid,
        Integer delayDays) {
}
