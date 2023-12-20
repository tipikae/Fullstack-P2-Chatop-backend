package com.tipikae.chatop.services.auth;

import com.tipikae.chatop.exceptions.AuthenticationException;
import com.tipikae.chatop.models.Token;
import org.springframework.security.core.Authentication;

/**
 * Authentication service interface.
 */
public interface IAuthService {

    /**
     * Generate a token with Authentication.
     * @param authentication Authentication object.
     * @return Token
     * @throws AuthenticationException thrown when an authentication exception occurred.
     */
    Token generateTokenWithAuthentication(Authentication authentication) throws AuthenticationException;

    /**
     * Generate a token with email.
     * @param email Email.
     * @return Token
     * @throws AuthenticationException thrown when an authentication exception occurred.
     */
    Token generateTokenWithEmail(String email) throws AuthenticationException;
}
