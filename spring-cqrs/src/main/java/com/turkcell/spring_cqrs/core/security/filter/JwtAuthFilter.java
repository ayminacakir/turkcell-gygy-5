package com.turkcell.spring_cqrs.core.security.filter;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.turkcell.spring_cqrs.core.security.context.UserContext;
import com.turkcell.spring_cqrs.core.security.jwt.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// Her istekte devreye gir, varsa JWT'i doğrula ve sisteme bak bu kişi şu jwt ile girdi bilgisini tanıt.. 

@Component
public class JwtAuthFilter extends OncePerRequestFilter { // OncePerRequestFilter şu demek:Her HTTP isteğinde sadece 1
                                                          // kez çalışır.
    private final JwtService jwtService;
    private final UserContext userContext;

    public JwtAuthFilter(JwtService jwtService, UserContext userContext) {
        this.jwtService = jwtService;
        this.userContext = userContext;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // request -> istek
        // response -> response'ın o ana kadarki oluşan halini
        // filterChain -> zincirin kendisi
        String jwtHeader = request.getHeader("Authorization");// request.getHeader("Authorization") ile frontend'den
                                                              // gelen token header'ı alınır.JWT genelde "Bearer token"
                                                              // formatında gönderilir.

        if (jwtHeader != null && jwtHeader.startsWith("Bearer ")) {
            String token = jwtHeader.substring(7); // substring(7) ile "Bearer " kısmı atılır ve sadece token elde
                                                   // edilir

            // JWT'i doğrula, kullanıcıyı bul ve sisteme tanıt..
            try {
                if (jwtService.isTokenValid(token)) {// token geçerli mi kontrol edilir.
                    // Kullanıcıyı sisteme tanıt..
                    String userId = jwtService.extractUserId(token);
                    String email = jwtService.extractEmail(token);
                    List<String> roles = jwtService.extractRoles(token);
                    userContext.setUser(userId, email, roles);
                }
            } catch (Exception e) {
                userContext.clear();
            }
        }

        filterChain.doFilter(request, response); // chaini ilerlet..
    }

}/*
  * Her HTTP isteğinde devreye girer.
  * Request header'dan Authorization bilgisini alır.
  * Token varsa JwtService'e sorar.
  * Token geçerliyse kullanıcıyı sisteme tanıtır.
  * Sonra isteği controller'a devam ettirir.
  */