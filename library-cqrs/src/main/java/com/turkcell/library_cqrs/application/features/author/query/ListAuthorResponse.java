package com.turkcell.library_cqrs.application.features.author.query;

import java.util.UUID;

public record ListAuthorResponse(UUID id, String firstname, String lastname, String biography) {

}
