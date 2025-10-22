package com.upiiz.alumnos.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT) // Esto asigna el 409
public class DataConflictException extends RuntimeException {
    public DataConflictException(String message) {
        super(message);
    }
}