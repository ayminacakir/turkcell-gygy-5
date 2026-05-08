package com.turkcell.library_cqrs.application.features.user.command.register;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.turkcell.library_cqrs.application.features.user.rule.UserBusinessRules;
import com.turkcell.library_cqrs.core.mediator.cqrs.CommandHandler;
import com.turkcell.library_cqrs.domain.entities.User;
import com.turkcell.library_cqrs.persistence.repositories.UserRepository;

@Component
public class RegisterCommandHandler implements CommandHandler<RegisterCommand, RegisterResponse> {

    private final UserRepository userRepository;
    private final UserBusinessRules userBusinessRules;
    private final PasswordEncoder passwordEncoder;

    public RegisterCommandHandler(
            UserRepository userRepository,
            UserBusinessRules userBusinessRules,
            PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userBusinessRules = userBusinessRules;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public RegisterResponse handle(RegisterCommand command) {

        userBusinessRules.userWithSameEmailMustNotExist(command.email());

        User user = new User();
        user.setEmail(command.email());

        user.setPassword(passwordEncoder.encode(command.password()));// 3. Şifreyi düz şekilde
                                                                     // kaydetmiyoruz:password1234 yerine BCrypt hash
                                                                     // kaydedilir.

        User savedUser = userRepository.save(user);

        return new RegisterResponse(savedUser.getId(), savedUser.getEmail());
    }
}