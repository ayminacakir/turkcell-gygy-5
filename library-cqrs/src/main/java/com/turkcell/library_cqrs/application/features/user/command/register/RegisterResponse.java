package com.turkcell.library_cqrs.application.features.user.command.register;

import java.util.UUID;

/*
 * Register işlemi başarılı olunca dönecek response.
 *
 * Şifre asla response olarak dönülmez.
 */
public record RegisterResponse(
        UUID id,
        String email) {
}