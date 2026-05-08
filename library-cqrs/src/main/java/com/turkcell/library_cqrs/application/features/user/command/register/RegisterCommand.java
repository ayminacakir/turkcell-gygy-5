package com.turkcell.library_cqrs.application.features.user.command.register;

import com.turkcell.library_cqrs.core.mediator.cqrs.Command;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterCommand(

        @NotBlank(message = "Email boş olamaz.") @Email(message = "Geçerli bir email giriniz.") String email,

        @NotBlank(message = "Şifre boş olamaz.") @Size(min = 3, message = "Şifre en az 3 karakter olmalıdır.") String password

) implements Command<RegisterResponse> {
}