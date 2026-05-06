package com.turkcell.library_cqrs.application.features.book.query;

import java.time.LocalDate;
import java.util.UUID;

import com.turkcell.library_cqrs.core.mediator.cqrs.Command;

public record ReturnBookCommand(
                UUID loanId,
                LocalDate returnDate) implements Command<UUID> {
}