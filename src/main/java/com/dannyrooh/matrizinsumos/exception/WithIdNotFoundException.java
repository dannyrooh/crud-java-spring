package com.dannyrooh.matrizinsumos.exception;

public class WithIdNotFoundException extends RuntimeException {

    public static final String ID_NOT_FOUND = "ID not found.";

    public WithIdNotFoundException() {
        super(ID_NOT_FOUND);
    }

    public WithIdNotFoundException(String message) {
        super((message == null || message.isEmpty()) ? ID_NOT_FOUND : message);
    }

    public WithIdNotFoundException(String message, Throwable cause) {
        super((message == null || message.isEmpty()) ? ID_NOT_FOUND : message, cause);
    }
}
