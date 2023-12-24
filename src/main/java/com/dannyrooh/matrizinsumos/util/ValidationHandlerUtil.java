package com.dannyrooh.matrizinsumos.util;

import java.util.HashMap;
import java.util.Map;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.dannyrooh.matrizinsumos.exception.WithIdZeroOrNotInformedException;

@ControllerAdvice
public class ValidationHandlerUtil extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        Map<String, String> errors = extractFieldErrors(ex);
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(WithIdZeroOrNotInformedException.class)
    public ResponseEntity<Object> handleIdShouldInformedException(WithIdZeroOrNotInformedException ex) {
        return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(buildErrorBody(ex.getMessage()));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex) {
        String message = "Violação de registro duplicado: " + ex.getMessage();
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(buildErrorBody(message));
    }

    private Map<String, String> extractFieldErrors(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });
        return errors;
    }

    private Map<String, Object> buildErrorBody(String message) {
        Map<String, Object> body = new HashMap<>();
        body.put("message", message);
        return body;
    }

}