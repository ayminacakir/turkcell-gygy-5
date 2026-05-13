package com.turkcell.spring_cqrs.core.security.authorization;

import java.util.List;

public interface AuthorizableRequest {
    // Varsayılan olarak özel rol istemez.
    // Yani sadece giriş yapılmış olması yeterlidir.
    default List<String> requiredRoles() {
        return List.of();
    }
}
