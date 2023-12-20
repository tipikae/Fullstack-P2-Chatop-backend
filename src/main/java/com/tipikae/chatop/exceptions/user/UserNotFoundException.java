package com.tipikae.chatop.exceptions.user;

import com.tipikae.chatop.exceptions.ChatopException;

import java.io.Serial;

/**
 * User not found exception.
 */
public class UserNotFoundException extends ChatopException {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Constructor.
     * @param message String
     */
    public UserNotFoundException(String message) {
        super(message);
    }
}
