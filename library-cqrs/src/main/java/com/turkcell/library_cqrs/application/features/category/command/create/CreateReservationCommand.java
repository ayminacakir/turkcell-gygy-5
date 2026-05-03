package com.turkcell.library_cqrs.application.features.category.command.create;

import java.util.UUID;

import com.turkcell.library_cqrs.core.mediator.cqrs.Command;

public record CreateReservationCommand(
        UUID bookId,
        UUID studentId) implements Command<UUID> {
}