package com.turkcell.library_cqrs.application.features.location.command;

import java.util.UUID;

import com.turkcell.library_cqrs.core.mediator.cqrs.Command;

public record CreateLocationCommand(
                String shelfNumber,
                Integer floor,
                String section) implements Command<UUID> {
}
