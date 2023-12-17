package com.tipikae.chatop.exceptions.storage;

import com.tipikae.chatop.exceptions.ChatopException;

import java.io.Serial;

/**
 * File not found exception.
 */
public class FileNotFoundException extends ChatopException  {

    @Serial
    private static final long serialVersionUID = 1L;

    public FileNotFoundException(String message) {
        super(message);
    }
}
