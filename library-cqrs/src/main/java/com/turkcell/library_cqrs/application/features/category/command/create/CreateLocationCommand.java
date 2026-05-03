package com.turkcell.library_cqrs.application.features.category.command.create;

import java.util.UUID;

import com.turkcell.library_cqrs.core.mediator.cqrs.Command;

public record CreateLocationCommand(
        String shelfNumber,
        Integer floor,
        String section) implements Command<UUID> {
}
