package com.tipikae.chatop.exceptions;

import java.io.Serial;

public class AuthenticationException extends ChatopException {

    @Serial
    private static final long serialVersionUID = 1L;

    public AuthenticationException(String message) {
        super(message);
    }
}
