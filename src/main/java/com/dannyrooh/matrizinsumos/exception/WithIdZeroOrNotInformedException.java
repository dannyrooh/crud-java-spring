package com.dannyrooh.matrizinsumos.exception;

public class WithIdZeroOrNotInformedException extends RuntimeException {

    public static final String ID_SHOULD_INFORMED = "ID deve ser informado.";

    public WithIdZeroOrNotInformedException() {
        super(ID_SHOULD_INFORMED);
    }

    public WithIdZeroOrNotInformedException(String message) {
        super((message == null || message.isEmpty()) ? ID_SHOULD_INFORMED : message);
    }

    public WithIdZeroOrNotInformedException(String message, Throwable cause) {
        super((message == null || message.isEmpty()) ? ID_SHOULD_INFORMED : message, cause);
    }
}
