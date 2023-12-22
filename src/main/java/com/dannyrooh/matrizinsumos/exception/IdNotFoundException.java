package com.dannyrooh.matrizinsumos.exception;

public class IdNotFoundException extends RuntimeException {

    public static final String ID_NOT_FOUND = "ID not found.";

    public IdNotFoundException() {
        super(ID_NOT_FOUND);
    }

    public IdNotFoundException(String message) {
        super((message == null || message.isEmpty()) ? ID_NOT_FOUND : message);
    }

    public IdNotFoundException(String message, Throwable cause) {
        super((message == null || message.isEmpty()) ? ID_NOT_FOUND : message, cause);
    }
}
