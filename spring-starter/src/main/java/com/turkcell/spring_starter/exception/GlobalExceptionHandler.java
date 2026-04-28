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
    public String handleBusinessException(BusinessException exception) {
        return exception.getMessage();
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

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNotFoundException(NotFoundException exception) {
        return exception.getMessage();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleException(Exception exception) {
        return "Beklenmeyen bir hata oluştu.";
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