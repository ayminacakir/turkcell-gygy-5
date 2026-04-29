package com.turkcell.spring_starter.exception;

public class ConflictException extends RuntimeException {
    public ConflictException(String message) {
        super(message);
    }

}
