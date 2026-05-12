package com.turkcell.library_cqrs.core.security.jwt;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

import javax.crypto.SecretKey;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

/*
 * JwtService:
 * Login başarılı olunca JWT token üretir.
 */
@Service
@EnableConfigurationProperties(JwtProperties.class)
public class JwtService {

    private final JwtProperties jwtProperties;
    private final SecretKey signingKey;

    public JwtService(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;

        /*
         * application.yml'deki secret değerini alıyoruz.
         * Base64 decode edip SecretKey haline getiriyoruz.
         */
        byte[] keyBytes = Decoders.BASE64.decode(jwtProperties.getSecret());
        this.signingKey = Keys.hmacShaKeyFor(keyBytes);
    }

    public String generate(UUID userId, String email) { // Kullanıcı için JWT token üretir
        Instant now = Instant.now();// Şu anki zamanı alır. Token'ın ne zaman oluşturulduğunu ve ne zaman geçersiz
                                    // olacağını belirlemek için kullanılır.

        return Jwts.builder() //
                .issuer(jwtProperties.getIssuer())
                .subject(userId.toString())
                .claim("email", email)
                .issuedAt(Date.from(now))
                .expiration(Date.from(now.plusSeconds(jwtProperties.getExpirationInSeconds())))
                .signWith(signingKey)
                .compact();
    }
}