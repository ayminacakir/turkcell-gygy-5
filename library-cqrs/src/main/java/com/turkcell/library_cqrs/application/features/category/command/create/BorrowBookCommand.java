package com.turkcell.library_cqrs.application.features.category.command.create;

import java.time.LocalDate;
import java.util.UUID;

import com.turkcell.library_cqrs.core.mediator.cqrs.Command;

public record BorrowBookCommand(
        UUID bookCopyId,
        UUID studentId,
        UUID staffId,
        LocalDate dueDate) implements Command<UUID> {
}
