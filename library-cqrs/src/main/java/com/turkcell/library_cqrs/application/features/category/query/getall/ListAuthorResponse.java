package com.turkcell.library_cqrs.application.features.category.query.getall;

import java.util.UUID;

public record ListAuthorResponse(UUID id, String firstname, String lastname, String biography) {

}
