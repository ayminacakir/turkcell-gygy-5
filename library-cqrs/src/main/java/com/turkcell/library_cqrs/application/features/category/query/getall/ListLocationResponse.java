package com.turkcell.library_cqrs.application.features.category.query.getall;

import java.util.UUID;

public record ListLocationResponse(
        UUID id,
        String shelfNumber,
        Integer floor,
        String section) {
}
