package com.turkcell.library_cqrs.application.features.category.command.create;

import java.time.LocalDate;
import java.util.UUID;

import com.turkcell.library_cqrs.core.mediator.cqrs.Command;

public record CreateStudentCommand(String firstName, String lastName, String email, String phoneNumber,
        LocalDate birthdate) implements Command<UUID> {

}
