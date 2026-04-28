package com.turkcell.spring_starter.exception;

public class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super(message);
    }

}
