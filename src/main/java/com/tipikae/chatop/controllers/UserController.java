package com.tipikae.chatop.controllers;

import com.tipikae.chatop.dto.user.UserDTO;
import com.tipikae.chatop.errorHandler.Error;
import com.tipikae.chatop.exceptions.ConverterDTOException;
import com.tipikae.chatop.exceptions.user.UserNotFoundException;
import com.tipikae.chatop.services.user.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * User controller.
 */
@RestController
@Validated
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    /**
     * Get a user by id.
     * @param id User's id.
     * @return UserDTO
     * @throws UserNotFoundException thrown when a user is not found.
     * @throws ConverterDTOException thrown when a converter exception occurred.
     */
    @Operation(summary = "Get user by id")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "User found and returned",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = UserDTO.class))}),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid parameter",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Error.class))}),
            @ApiResponse(
                    responseCode = "401",
                    description = "Not authorized",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Error.class))}),
            @ApiResponse(
                    responseCode = "404",
                    description = "User not found",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Error.class))}),
    })
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable("id") @NotNull @Positive long id)
            throws UserNotFoundException, ConverterDTOException {
        UserDTO userDTO = userService.getUserById(id);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }
}
