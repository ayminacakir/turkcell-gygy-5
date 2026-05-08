package com.turkcell.library_cqrs.core.security.jwt;

import org.springframework.boot.context.properties.ConfigurationProperties;

/*
 * JwtProperties:
 * application.yml içindeki security.jwt değerlerini Java'ya taşır.
 *
 * application.yml:
 *
 * security:
 *   jwt:
 *     secret: ...
 *     expirationInSeconds: 360000
 *     issuer: library-cqrs
 */
@ConfigurationProperties(prefix = "security.jwt")
public class JwtProperties {

    private String secret; // Token imzalamak için kullanılan gizli anahtar.

    private long expirationInSeconds = 360000; // Token kaç saniye geçerli olacak?

    private String issuer = "library-cqrs"; // Token'ı hangi uygulama üretti?

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public long getExpirationInSeconds() {
        return expirationInSeconds;
    }

    public void setExpirationInSeconds(long expirationInSeconds) {
        this.expirationInSeconds = expirationInSeconds;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }
}