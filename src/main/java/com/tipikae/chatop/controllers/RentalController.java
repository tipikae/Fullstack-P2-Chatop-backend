package com.tipikae.chatop.controllers;

import com.tipikae.chatop.dto.rental.NewRentalDTO;
import com.tipikae.chatop.dto.rental.RentalDTO;
import com.tipikae.chatop.dto.rental.UpdateRentalDTO;
import com.tipikae.chatop.dto.user.UserDTO;
import com.tipikae.chatop.errorHandler.Error;
import com.tipikae.chatop.exceptions.ConverterDTOException;
import com.tipikae.chatop.exceptions.rental.RentalNotFoundException;
import com.tipikae.chatop.exceptions.storage.FileNotFoundException;
import com.tipikae.chatop.exceptions.storage.StorageException;
import com.tipikae.chatop.exceptions.user.UserNotFoundException;
import com.tipikae.chatop.models.RentalsResponse;
import com.tipikae.chatop.models.Response;
import com.tipikae.chatop.services.rental.IRentalService;
import com.tipikae.chatop.services.storage.IStorageService;
import com.tipikae.chatop.services.user.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;

/**
 * Rental controller.
 */
@RestController
@RequestMapping("/rentals")
@Validated
public class RentalController {

    @Autowired
    private IRentalService rentalService;

    @Autowired
    private IStorageService storageService;

    @Autowired
    private IUserService userService;

    /**
     * Create a rental.
     * @param newRentalDTO NewRentalDTO object.
     * @return ResponseEntity
     * @throws UserNotFoundException thrown when owner is not found.
     * @throws ConverterDTOException thrown when a converter exception occurred.
     * @throws StorageException thrown when a storage exception occurred.
     */
    @Operation(summary = "Create a new Rental")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Rental created",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Response.class))}),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid parameters",
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
    @PostMapping( value = "", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE } )
    public ResponseEntity<Response> createRental(
            @ModelAttribute @Valid NewRentalDTO newRentalDTO,
            Principal principal)
                throws UserNotFoundException, ConverterDTOException, StorageException {

        long ownerId = userService.getUserByEmail(principal.getName()).getId();
        rentalService.createRental(newRentalDTO, ownerId);
        Response response = new Response("Rental created !");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Get all rentals.
     * @return ResponseEntity
     * @throws ConverterDTOException thrown when a converter exception occurred.
     */
    @Operation(summary = "Get all rentals")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Rentals returned",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = RentalsResponse.class))}),
            @ApiResponse(
                    responseCode = "401",
                    description = "Not authorized",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Error.class))}),
    })
    @GetMapping("")
    public ResponseEntity<RentalsResponse> getAllRentals() throws ConverterDTOException {
        List<RentalDTO> rentals = rentalService.getAllRentals();
        RentalsResponse rentalsResponse = new RentalsResponse(rentals);
        return new ResponseEntity<>(rentalsResponse, HttpStatus.OK);
    }

    /**
     * Get a rental by id.
     * @param id Rental's id.
     * @return ResponseEntity
     * @throws RentalNotFoundException thrown when rental is not found.
     * @throws ConverterDTOException thrown when a converter exception occurred.
     */
    @Operation(summary = "Get a rental by id")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Rental found and returned",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = RentalDTO.class))}),
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
                    description = "Rental not found",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Error.class))}),
    })
    @GetMapping("/{id}")
    public ResponseEntity<RentalDTO> getRentalById(@PathVariable("id") @NotNull @Positive long id)
            throws RentalNotFoundException, ConverterDTOException {
        RentalDTO rentalDTO = rentalService.getRentalById(id);
        return new ResponseEntity<>(rentalDTO, HttpStatus.OK);
    }

    /**
     * Update a rental.
     * @param id Rental's id.
     * @param updateRentalDTO UpdateRentalDTO object.
     * @return ResponseEntity
     * @throws UserNotFoundException thrown when owner is not found.
     * @throws RentalNotFoundException thrown when rental is not found.
     * @throws ConverterDTOException thrown when a converter exception occurred.
     */
    @Operation(summary = "Update a rental")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Rental updated",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Response.class))}),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid parameters",
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
                    description = "User or Rental not found",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Error.class))}),
    })
    @PutMapping( value = "/{id}", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE } )
    public ResponseEntity<Response> updateRental(
            @PathVariable("id") @NotNull @Positive long id,
            @ModelAttribute @Valid UpdateRentalDTO updateRentalDTO )
                throws UserNotFoundException, RentalNotFoundException, ConverterDTOException {

        rentalService.updateRental(id, updateRentalDTO);
        Response response = new Response("Rental updated !");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Download a picture.
     * @param filename File name.
     * @return ResponseEntity
     * @throws FileNotFoundException thrown when file is not found.
     * @throws StorageException thrown when a storage exception occurred.
     */
    @Operation(summary = "Load a picture")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Picture downloaded",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Resource.class))}),
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
                    description = "Picture not found",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Error.class))}),
    })
    @GetMapping("/uploads/{filename}")
    public ResponseEntity<Resource> loadPicture(@PathVariable("filename") @NotBlank String filename)
            throws FileNotFoundException, StorageException {
        Resource resource = storageService.load(filename);
        return new ResponseEntity<>(resource, HttpStatus.OK);
    }
}
