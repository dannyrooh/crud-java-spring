package com.dannyrooh.matrizinsumos.util;

import org.springframework.web.bind.annotation.ResponseStatus;

import com.dannyrooh.matrizinsumos.exception.WithIdZeroOrNotInformedException;
import org.springframework.http.HttpStatus;

public class ValidatorFieldsUtil {

    @ResponseStatus(HttpStatus.NOT_MODIFIED)
    public static void validateID(Integer id) {
        if (id == null || id <= 0) {
            throw new WithIdZeroOrNotInformedException();
        }
    }

    @ResponseStatus(HttpStatus.NOT_MODIFIED)
    public static void validateID(Integer id, String message) {
        if (id == null || id <= 0) {
            throw new WithIdZeroOrNotInformedException(message);
        }
    }

}
