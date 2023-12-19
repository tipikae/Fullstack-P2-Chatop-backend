package com.tipikae.chatop.models;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Login request model.
 */
@Data
@AllArgsConstructor
public class LoginRequest {

    @NotBlank(message = "Login must not be blank.")
    private String login;

    @NotBlank(message = "Password must not be blank.")
    private String password;
}
