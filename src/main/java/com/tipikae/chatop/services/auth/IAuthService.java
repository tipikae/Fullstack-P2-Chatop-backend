package com.tipikae.chatop.services.auth;

import com.tipikae.chatop.models.Token;
import org.springframework.security.core.Authentication;

/**
 * JWT service interface.
 */
public interface IAuthService {

    /**
     * Generate a token.
     * @param authentication Authentication object.
     * @return Token
     */
    Token generateToken(Authentication authentication);
}
