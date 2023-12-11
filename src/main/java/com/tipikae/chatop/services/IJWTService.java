package com.tipikae.chatop.services;

import com.tipikae.chatop.models.Token;
import org.springframework.security.core.Authentication;

/**
 * JWT service interface.
 */
public interface IJWTService {

    /**
     * Generate a token.
     * @param authentication Authentication object.
     * @return Token
     */
    Token generateToken(Authentication authentication);
}
