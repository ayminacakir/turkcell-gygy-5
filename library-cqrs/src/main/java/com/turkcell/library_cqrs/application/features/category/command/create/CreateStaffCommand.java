package com.turkcell.library_cqrs.application.features.category.command.create;

import java.util.UUID;

import com.turkcell.library_cqrs.core.mediator.cqrs.Command;

public record CreateStaffCommand(
        String firstName,
        String lastName,
        String username,
        String passwordHash,
        String role) implements Command<UUID> {
}
