package com.turkcell.library_cqrs.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turkcell.library_cqrs.application.features.user.command.login.LoginCommand;
import com.turkcell.library_cqrs.application.features.user.command.login.LoginResponse;
import com.turkcell.library_cqrs.application.features.user.command.register.RegisterCommand;
import com.turkcell.library_cqrs.application.features.user.command.register.RegisterResponse;
import com.turkcell.library_cqrs.core.mediator.Mediator;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final Mediator mediator;

    public AuthController(Mediator mediator) {
        this.mediator = mediator;
    }

    @PostMapping("/register")
    public RegisterResponse register(@Valid @RequestBody RegisterCommand command) {
        return mediator.send(command);
    }

    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody LoginCommand command) {
        return mediator.send(command);
    }
}
