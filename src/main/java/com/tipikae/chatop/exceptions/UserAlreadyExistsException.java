package com.tipikae.chatop.exceptions;

import java.io.Serial;

/**
 * User already exists exception.
 */
public class UserAlreadyExistsException extends ChatopException {

    @Serial
    private static final long serialVersionUID = 1L;

    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
