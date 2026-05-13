package com.turkcell.spring_cqrs.core.security.exception;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.turkcell.spring_cqrs.core.security.exception.UnauthorizedException;
import com.turkcell.spring_cqrs.core.security.exception.UnauthenticatedException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UnauthenticatedException.class)
    public ResponseEntity<Map<String, String>> handleUnauthenticatedException(
            UnauthenticatedException exception) {

        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED) // 401
                .body(Map.of("message", exception.getMessage()));
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<Map<String, String>> handleUnauthorizedException(
            UnauthorizedException exception) {

        return ResponseEntity
                .status(HttpStatus.FORBIDDEN) // 403
                .body(Map.of("message", exception.getMessage()));
    }
}