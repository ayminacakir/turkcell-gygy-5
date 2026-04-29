package com.turkcell.spring_starter.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//Ödev:Bilindik hata türleri için yönetimi düzgünleştir.
//RuntimeException,çok genel bir hata türü olduğu için,kendimize özel exception türleri yaratıp onları yakalamak.(Business Exception)
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleBusinessException(BusinessException exception) {
        return new ErrorResponse(exception.getMessage(), 400);
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleNotFoundException(NotFoundException exception) {
        return new ErrorResponse(exception.getMessage(), 404);
    }

    @ExceptionHandler({ MethodArgumentNotValidException.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleValidationException(MethodArgumentNotValidException exception) {

        Map<String, String> errors = new HashMap<>();

        exception.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });

        return errors;
    }

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponse handleUnauthorizedException(UnauthorizedException exception) {
        return new ErrorResponse(exception.getMessage(), 401);
    }

    @ExceptionHandler(ConflictException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleConflictException(ConflictException exception) {
        return new ErrorResponse(exception.getMessage(), 409);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleException(Exception exception) {
        return new ErrorResponse("Beklenmeyen bir hata oluştu.", 500);
    }
}

/*
 * 1. “Bilindik hata türleri için yönetimi düzgünleştir.”
 * 
 * Bunu GlobalExceptionHandler içinde yaptık.
 * 
 * Mesela:
 * 
 * @ExceptionHandler(BusinessException.class)-> Business hatalarını ayrı
 * yakaladık.
 * 
 * @ExceptionHandler(NotFoundException.class) -> Bulunamadı hatalarını ayrı
 * yakaladık.
 * 
 * @ExceptionHandler(MethodArgumentNotValidException.class) -> Validation
 * hatalarını ayrı yakaladık.
 * 
 * Yani artık her hata aynı yerde RuntimeException diye yakalanmıyor.
 */