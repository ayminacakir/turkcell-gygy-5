package com.turkcell.spring_cqrs.core.security.jwt;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
@EnableConfigurationProperties(JwtProperties.class)
public class JwtService {
    private final JwtProperties jwtProperties;
    private final SecretKey signingKey;

    public JwtService(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;

        byte[] keyBytes = Decoders.BASE64.decode(jwtProperties.getSecret());
        this.signingKey = Keys.hmacShaKeyFor(keyBytes);
    }

    public String generate(UUID userId, String email) {
        Instant now = Instant.now();
        return Jwts.builder()
                .issuer(this.jwtProperties.getIssuer())
                .subject(userId.toString())
                .claim("email", email)
                .claim("deneme", "deneme")
                .issuedAt(Date.from(now))
                .expiration(Date.from(now.plusSeconds(this.jwtProperties.getExpirationInSeconds())))
                .signWith(this.signingKey)
                .compact();
    }

    public String extractUserId(String token) { // JWT içinde genellikle kullanıcı ID'si "subject" alanına yazılır
        return extractClaim(token, Claims::getSubject); // Claims::getSubject demek: claims nesnesinden subject alanını
                                                        // al.claims -> claims.getSubject()
    }

    // JWT içinde bilgiler vardır. Bunlara claim denir.

    public String extractEmail(String token) {
        return extractClaim(token, claims -> claims.get("email", String.class));
    }

    public boolean isTokenValid(String token) {
        try {
            return !extractClaim(token, Claims::getExpiration).before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    // <T> demek: dönüş tipi değişebilir.
    // Mesela userId çekerken String döner.
    // Expiration çekerken Date döner.
    private <T> T extractClaim(String token, Function<Claims, T> resolver) { // resolver, Claims içinden hangi bilginin
                                                                             // alınacağını belirleyen fonksiyondur.
        Claims claims = Jwts.parser()// JWT token parse ediliyor, yani okunabilir hale getiriliyor.
                .verifyWith(signingKey)
                .build()
                .parseSignedClaims(token)
                // Token'ın payload kısmını alıyoruz.
                // Payload içinde userId, email, expiration gibi bilgiler bulunur.
                .getPayload();

        return resolver.apply(claims);
    }
}