package com.upiiz.alumnos.exception;

import com.upiiz.alumnos.response.RespuestaApi;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice // Intercepta excepciones de todos los @Controller
public class GlobalExceptionHandler {

    // Maneja 404 (No Encontrado)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<RespuestaApi<Object>> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        RespuestaApi<Object> respuesta = new RespuestaApi<>("NOT_FOUND", ex.getMessage());
        return new ResponseEntity<>(respuesta, HttpStatus.NOT_FOUND);
    }

    // Maneja 409 (Conflicto / Duplicado)
    @ExceptionHandler(DataConflictException.class)
    public ResponseEntity<RespuestaApi<Object>> handleDataConflictException(DataConflictException ex, WebRequest request) {
        RespuestaApi<Object> respuesta = new RespuestaApi<>("CONFLICT", ex.getMessage());
        return new ResponseEntity<>(respuesta, HttpStatus.CONFLICT);
    }

    // Maneja 400 (Datos Inválidos - ej. si usas @Valid en el Controller)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<RespuestaApi<Object>> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, WebRequest request) {
        // Aquí podrías listar los campos que fallaron
        RespuestaApi<Object> respuesta = new RespuestaApi<>("BAD_REQUEST", "Datos inválidos o incompletos.");
        return new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
    }

    // Maneja 500 (Error genérico del servidor)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<RespuestaApi<Object>> handleGlobalException(Exception ex, WebRequest request) {
        RespuestaApi<Object> respuesta = new RespuestaApi<>("INTERNAL_SERVER_ERROR", "Error del servidor: " + ex.getMessage());
        return new ResponseEntity<>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}