package com.turkcell.library_cqrs.application.features.category.query.getall;

import java.time.LocalDate;
import java.util.UUID;

public record ListStudentResponse(
        UUID id,
        String firstName,
        String lastName,
        String email,
        String phoneNumber,
        LocalDate membershipDate,
        LocalDate birthdate) {

}
