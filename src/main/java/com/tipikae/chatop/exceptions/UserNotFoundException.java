package com.tipikae.chatop.exceptions;

import java.io.Serial;

/**
 * User not found exception.
 */
public class UserNotFoundException extends ChatopException {

    @Serial
    private static final long serialVersionUID = 1L;

    public UserNotFoundException(String message) {
        super(message);
    }
}
