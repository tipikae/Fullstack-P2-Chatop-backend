package com.tipikae.chatop.exceptions;

import java.io.Serial;

/**
 * Parent exception for all others.
 */
public class ChatopException extends Exception {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Constructor.
     * @param message String
     */
    public ChatopException(String message) {
        super(message);
    }
}
