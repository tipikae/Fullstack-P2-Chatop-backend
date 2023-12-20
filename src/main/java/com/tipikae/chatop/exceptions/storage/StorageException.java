package com.tipikae.chatop.exceptions.storage;

import com.tipikae.chatop.exceptions.ChatopException;

import java.io.Serial;

/**
 * Storage exception.
 */
public class StorageException extends ChatopException {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Constructor.
     * @param message String
     */
    public StorageException(String message) {
        super(message);
    }
}
