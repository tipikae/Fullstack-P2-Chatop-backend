package com.tipikae.chatop.services.auth;

import com.tipikae.chatop.exceptions.AuthenticationException;
import com.tipikae.chatop.models.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

/**
 * Authentication service.
 */
@Service
public class AuthService implements IAuthService {

    @Autowired
    private JwtEncoder jwtEncoder;

    /**
     * Generate a token.
     * @param authentication Authentication object.
     * @return Token
     * @throws AuthenticationException thrown when an authentication exception occurred.
     */
    @Override
    public Token generateTokenWithAuthentication(Authentication authentication) throws AuthenticationException {
        return generateToken(authentication.getName());
    }

    /**
     * Generate a token with email.
     *
     * @param email Email.
     * @return Token
     * @throws AuthenticationException thrown when an authentication exception occurred.
     */
    @Override
    public Token generateTokenWithEmail(String email) throws AuthenticationException {
        return generateToken(email);
    }

    private Token generateToken(String email) throws AuthenticationException {
        Instant now = Instant.now();
        JwtClaimsSet claims =
                JwtClaimsSet.builder()
                        .issuer("self")
                        .issuedAt(now)
                        .expiresAt(now.plus(1, ChronoUnit.DAYS))
                        .subject(email)
                        .build();
        JwtEncoderParameters jwtEncoderParameters =
                JwtEncoderParameters.from(JwsHeader.with(MacAlgorithm.HS256).build(), claims);

        try {
            return new Token(this.jwtEncoder.encode(jwtEncoderParameters).getTokenValue());
        } catch (JwtEncodingException e) {
            throw new AuthenticationException(e.getMessage());
        }
    }
}
