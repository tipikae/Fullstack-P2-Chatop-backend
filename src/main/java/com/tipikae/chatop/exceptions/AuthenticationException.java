package com.tipikae.chatop.exceptions;

import java.io.Serial;

/**
 * Authentication exception.
 */
public class AuthenticationException extends ChatopException {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Constructor.
     * @param message String
     */
    public AuthenticationException(String message) {
        super(message);
    }
}
