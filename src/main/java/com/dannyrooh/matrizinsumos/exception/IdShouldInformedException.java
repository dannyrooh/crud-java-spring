package com.dannyrooh.matrizinsumos.exception;

public class IdShouldInformedException extends RuntimeException {

    public static final String ID_SHOULD_INFORMED = "ID deve ser informado.";

    public IdShouldInformedException() {
        super(ID_SHOULD_INFORMED);
    }

    public IdShouldInformedException(String message) {
        super((message == null || message.isEmpty()) ? ID_SHOULD_INFORMED : message);
    }

    public IdShouldInformedException(String message, Throwable cause) {
        super((message == null || message.isEmpty()) ? ID_SHOULD_INFORMED : message, cause);
    }
}
