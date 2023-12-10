package com.tipikae.chatop.controllers;

import com.tipikae.chatop.models.Token;
import com.tipikae.chatop.services.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private JWTService jwtService;

    @PostMapping("/login")
    public Token login(Authentication authentication) {
        return jwtService.generateToken(authentication);
    }

    @PostMapping("/register")
    public String register() {
        return "registered";
    }
}
