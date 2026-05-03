package com.turkcell.library_cqrs.application.features.category.command.create;

import java.util.Set;
import java.util.UUID;

import com.turkcell.library_cqrs.core.mediator.cqrs.Command;

public record CreateBookCommand(
        String isbn,
        String title,
        String publisher,
        Integer publicationYear,
        Set<UUID> authorIds,
        Set<UUID> categoryIds) implements Command<UUID> {
}