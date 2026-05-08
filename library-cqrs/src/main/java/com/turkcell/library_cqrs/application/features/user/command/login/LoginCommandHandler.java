package com.turkcell.library_cqrs.application.features.user.command.login;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.turkcell.library_cqrs.core.mediator.cqrs.CommandHandler;
import com.turkcell.library_cqrs.core.security.jwt.JwtService;
import com.turkcell.library_cqrs.domain.entities.User;
import com.turkcell.library_cqrs.persistence.repositories.UserRepository;

/*
 * LoginCommandHandler:
 * Kullanıcı login olduğunda çalışan handler.
 *
 * Görevi:
 * 1. Email ile kullanıcıyı bulmak
 * 2. Şifre doğru mu kontrol etmek
 * 3. Doğruysa JWT üretmek
 */
@Component
public class LoginCommandHandler implements CommandHandler<LoginCommand, LoginResponse> {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public LoginCommandHandler(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @Override
    public LoginResponse handle(LoginCommand command) {

        /*
         * 1. Email ile kullanıcı bulunur.
         *
         * Kullanıcı yoksa genel bir hata veriyoruz.
         * "Email yanlış" demiyoruz.
         * Güvenlik açısından "Invalid credentials" daha doğru.
         */
        User user = userRepository.findByEmail(command.email())
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        /*
         * 2. Girilen düz şifre ile DB'deki hashli şifre karşılaştırılır.
         *
         * command.password() -> kullanıcının girdiği düz şifre
         * user.getPassword() -> DB'deki BCrypt hash
         */
        boolean passwordMatches = passwordEncoder.matches(command.password(), user.getPassword());

        if (!passwordMatches) {
            throw new RuntimeException("Invalid credentials"); // ikisine de aynı hatayı gönder
        }

        String token = jwtService.generate(user.getId(), user.getEmail()); // Email ve şifre doğruysa JWT token
                                                                           // üretilir.
        return new LoginResponse(token);
    }
}