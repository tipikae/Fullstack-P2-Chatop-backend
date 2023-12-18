package com.tipikae.chatop.controllers;

import com.tipikae.chatop.dto.user.NewUserDTO;
import com.tipikae.chatop.dto.user.UserDTO;
import com.tipikae.chatop.exceptions.AuthenticationException;
import com.tipikae.chatop.exceptions.ConverterDTOException;
import com.tipikae.chatop.exceptions.user.UserAlreadyExistsException;
import com.tipikae.chatop.exceptions.user.UserNotFoundException;
import com.tipikae.chatop.models.Token;
import com.tipikae.chatop.services.auth.IAuthService;
import com.tipikae.chatop.services.user.IUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/auth")
@Validated
public class AuthController {

    @Autowired
    private IAuthService authService;

    @Autowired
    private IUserService userService;

    /**
     * Login endpoint.
     * @param authentication Authentication object.
     * @return ResponseEntity<Token>
     * @throws AuthenticationException thrown when an authentication exception occurred.
     */
    @PostMapping("/login")
    public ResponseEntity<Token> login(Authentication authentication) throws AuthenticationException {
        Token token = authService.generateTokenWithAuthentication(authentication);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    /**
     * User registration endpoint.
     * @param newUserDTO User's information.
     * @return ResponseEntity<Token>
     * @throws UserAlreadyExistsException thrown when a user already exists.
     * @throws ConverterDTOException thrown when an DTO converter exception occurred.
     * @throws AuthenticationException thrown when an authentication exception occurred.
     */
    @PostMapping(value = "/register", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Token> register(@RequestBody @Valid NewUserDTO newUserDTO)
            throws UserAlreadyExistsException, ConverterDTOException, AuthenticationException {

        userService.createUser(newUserDTO);
        Token token = authService.generateTokenWithEmail(newUserDTO.getEmail());
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    /**
     * Get user's profile endpoint.
     * @param principal Principal object.
     * @return ResponseEntity<UserDTO>
     * @throws UserNotFoundException thrown when an DTO converter exception occurred.
     * @throws ConverterDTOException thrown when an DTO converter exception occurred.
     */
    @GetMapping("/me")
    public ResponseEntity<UserDTO> me(Principal principal) throws UserNotFoundException, ConverterDTOException {
        String email = principal.getName();
        UserDTO userDTO = userService.getUserByEmail(email);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }
}
