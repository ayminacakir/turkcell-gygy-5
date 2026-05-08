package com.turkcell.library_cqrs.application.features.user.rule;

import org.springframework.stereotype.Component;

import com.turkcell.library_cqrs.persistence.repositories.UserRepository;

@Component
public class UserBusinessRules {

    private final UserRepository userRepository;

    public UserBusinessRules(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void userWithSameEmailMustNotExist(String email) {
        boolean exists = userRepository.existsByEmail(email);

        if (exists) {
            throw new RuntimeException("Bu email ile kayıtlı kullanıcı zaten var.");
        }
    }
}
