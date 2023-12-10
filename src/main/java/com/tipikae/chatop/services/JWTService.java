package com.tipikae.chatop.services;

import com.tipikae.chatop.models.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

/**
 * JWT service.
 */
@Service
public class JWTService {

    @Autowired
    private JwtEncoder jwtEncoder;

    /**
     * Generate a token.
     * @param authentication Authentication object.
     * @return Token
     */
    public Token generateToken(Authentication authentication) {
        Instant now = Instant.now();
        JwtClaimsSet claims =
                JwtClaimsSet.builder()
                    .issuer("self")
                    .issuedAt(now)
                    .expiresAt(now.plus(1, ChronoUnit.DAYS))
                    .subject(authentication.getName())
                    .build();
        JwtEncoderParameters jwtEncoderParameters =
                JwtEncoderParameters.from(JwsHeader.with(MacAlgorithm.HS256).build(), claims);

        return new Token(this.jwtEncoder.encode(jwtEncoderParameters).getTokenValue());
    }
}
