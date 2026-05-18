package com.turkcell.spring_cqrs.core.security.authorization;

import java.util.List;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.turkcell.spring_cqrs.core.mediator.pipeline.PipelineBehavior;
import com.turkcell.spring_cqrs.core.mediator.pipeline.RequestHandlerDelegate;
import com.turkcell.spring_cqrs.core.security.context.UserContext;
import com.turkcell.spring_cqrs.core.security.exception.UnauthenticatedException;
import com.turkcell.spring_cqrs.core.security.exception.UnauthorizedException;

@Component
@Order(10)
public class AuthorizationBehavior implements PipelineBehavior {
    private final UserContext userContext;// UserContext, o anki isteği yapan kullanıcı bilgisini tutar.

    public AuthorizationBehavior(UserContext userContext) {
        this.userContext = userContext;
    }

    @Override
    public boolean supports(Object request) { // bu behavior'ın hangi request'lerde çalışacağını belirler. Sadece
                                              // AuthorizableRequest türündeki request'lerde çalışır.
        return request instanceof AuthorizableRequest;
    }

    // ilgili handler'ın öncesi ve sonrası çalıştırabilen kodlar.
    @Override
    public <R> R handle(Object request, RequestHandlerDelegate<R> next) {

        // Kullanıcı giriş yapmamışsa 401 dönecek exception fırlatılır.
        if (!userContext.isAuthenticated()) {
            throw new UnauthenticatedException("Giriş yapmalısın.");
        }

        AuthorizableRequest authorizableRequest = (AuthorizableRequest) request;

        // Request özel bir rol istiyor mu?
        List<String> requiredRoles = authorizableRequest.requiredRoles();

        // Eğer role listesi doluysa rol kontrolü yapılır.
        if (requiredRoles != null && !requiredRoles.isEmpty()) {

            boolean hasRequiredRole = requiredRoles.stream()
                    .anyMatch(requiredRole -> userContext.getRoles().contains(requiredRole));

            // Kullanıcı giriş yapmış ama rolü yetmiyorsa 403 dönecek exception fırlatılır.
            if (!hasRequiredRole) {
                throw new UnauthorizedException("Bu işlem için yetkin yok.");
            }
        }

        // Login varsa ve rol uygunsa zincir devam eder.
        return next.invoke();
    }

}