package com.tipikae.chatop.exceptions.rental;

import com.tipikae.chatop.exceptions.ChatopException;

import java.io.Serial;

/**
 * Rental not found exception.
 */
public class RentalNotFoundException extends ChatopException {

    @Serial
    private static final long serialVersionUID = 1L;

    public RentalNotFoundException(String message) {
        super(message);
    }
}
