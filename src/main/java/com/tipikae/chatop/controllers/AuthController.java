package com.tipikae.chatop.controllers;

import com.tipikae.chatop.models.Token;
import com.tipikae.chatop.services.IJWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private IJWTService jwtService;

    @PostMapping("/login")
    public ResponseEntity<Token> login(Authentication authentication) {
        Token token = jwtService.generateToken(authentication);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register() {
        String response = "registered";
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
