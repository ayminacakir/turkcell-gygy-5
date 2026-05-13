package com.turkcell.spring_cqrs.core.security.context;

import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import com.turkcell.spring_cqrs.core.security.exception.UnauthenticatedException;
import com.turkcell.spring_cqrs.core.security.exception.UnauthorizedException;

@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS) // Her HTTP isteği için yeni bir UserContext
                                                                    // oluşturulur.
public class UserContext {
    private String userId;
    private String email;
    private List<String> roles = Collections.emptyList();
    private boolean isAuthenticated = false;

    // JwtAuthFilter token'ı doğruladıktan sonra burayı çağırır.
    public void setUser(String userId, String email, List<String> roles) {
        this.userId = userId;
        this.email = email;
        this.roles = roles == null ? Collections.emptyList() : roles;
        this.isAuthenticated = true;
    }

    // Token geçersizse veya kullanıcı temizlenmek istenirse kullanılır.
    public void clear() {
        this.userId = null;
        this.email = null;
        this.roles = Collections.emptyList();
        this.isAuthenticated = false;
    }

    public void requireAuthenticated() {
        if (!this.isAuthenticated) {
            throw new UnauthenticatedException("Bu işlem için giriş yapmalısın.");
        }
    }

    public void requireRole(List<String> requiredRoles) {
        if (requiredRoles == null || requiredRoles.isEmpty()) {
            return;
        }
        boolean hasRole = requiredRoles.stream()
                .anyMatch(this.roles::contains);
        if (!hasRole) {
            throw new UnauthorizedException("Bu işlem için yetkin yok.");
        }
    }

    public String getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public List<String> getRoles() {
        return roles;
    }

    public boolean isAuthenticated() {
        return isAuthenticated;
    }

}
/*
 * UserContext = Şu anki isteği yapan kullanıcıyı uygulama içinde temsil eden
 * merkezi sınıf.
 * 
 * JwtAuthFilter token'ı doğrular.
 * Token geçerliyse userId/email/roles bilgilerini UserContext'e yazar.
 * Sonra uygulama istediği yerde UserContext'ten mevcut kullanıcıyı okuyabilir.
 */