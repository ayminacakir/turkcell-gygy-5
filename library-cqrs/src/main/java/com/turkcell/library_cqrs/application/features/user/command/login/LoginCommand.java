package com.turkcell.library_cqrs.application.features.user.command.login;

import com.turkcell.library_cqrs.core.logging.NotLoggableRequest;
import com.turkcell.library_cqrs.core.mediator.cqrs.Command;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/*
 * NotLoggableRequest:
 * Bu command içinde password olduğu için ileride LoggingBehavior bunu loglamasın diye işaretliyoruz.
 */
public record LoginCommand(

        @NotBlank(message = "Email boş olamaz.") @Email(message = "Geçerli bir email giriniz.") String email,

        @NotBlank(message = "Şifre boş olamaz.") String password

) implements Command<LoginResponse>, NotLoggableRequest {
}
