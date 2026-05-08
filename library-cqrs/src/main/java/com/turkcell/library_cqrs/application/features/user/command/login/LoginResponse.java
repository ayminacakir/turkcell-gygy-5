package com.turkcell.library_cqrs.application.features.user.command.login;

public record LoginResponse( // Login başarılı olunca frontend/Postman'a token döneceğiz.
        String accessToken) {
}