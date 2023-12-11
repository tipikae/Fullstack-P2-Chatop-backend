package com.tipikae.chatop.exceptions;

import java.io.Serial;

/**
 * Converter DTO exception
 */
public class ConverterDTOException extends ChatopException {

    @Serial
    private static final long serialVersionUID = 1L;

    public ConverterDTOException(String message) {
        super(message);
    }
}
