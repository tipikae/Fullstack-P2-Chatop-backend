package com.tipikae.chatop.controllers;

import com.tipikae.chatop.dto.user.NewUserDTO;
import com.tipikae.chatop.dto.user.UserDTO;
import com.tipikae.chatop.exceptions.AuthenticationException;
import com.tipikae.chatop.exceptions.ConverterDTOException;
import com.tipikae.chatop.exceptions.user.UserAlreadyExistsException;
import com.tipikae.chatop.exceptions.user.UserNotFoundException;
import com.tipikae.chatop.models.LoginRequest;
import com.tipikae.chatop.models.Token;
import com.tipikae.chatop.services.auth.IAuthService;
import com.tipikae.chatop.services.user.IUserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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

    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * Login endpoint.
     * @param loginRequest LoginRequest object.
     * @return ResponseEntity<Token>
     * @throws AuthenticationException thrown when an authentication exception occurred.
     */
    @PostMapping(value = "/login", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Token> login(@RequestBody @Valid LoginRequest loginRequest)
            throws AuthenticationException {
        Authentication authenticationRequest =
                UsernamePasswordAuthenticationToken.unauthenticated(loginRequest.getLogin(), loginRequest.getPassword());
        Authentication authenticationResponse =
                authenticationManager.authenticate(authenticationRequest);
        Token token = authService.generateTokenWithAuthentication(authenticationResponse);
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
