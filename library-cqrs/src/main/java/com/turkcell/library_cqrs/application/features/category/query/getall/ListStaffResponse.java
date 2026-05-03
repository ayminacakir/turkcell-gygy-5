package com.turkcell.library_cqrs.application.features.category.query.getall;

import java.util.UUID;

public record ListStaffResponse(
                UUID id,
                String firstName,
                String lastName,
                String username,
                String role) {
}