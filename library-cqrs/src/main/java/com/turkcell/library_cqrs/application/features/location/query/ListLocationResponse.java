package com.turkcell.library_cqrs.application.features.location.query;

import java.util.UUID;

public record ListLocationResponse(
                UUID id,
                String shelfNumber,
                Integer floor,
                String section) {
}
