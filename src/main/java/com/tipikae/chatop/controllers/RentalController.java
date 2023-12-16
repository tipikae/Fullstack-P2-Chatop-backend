package com.tipikae.chatop.controllers;

import com.tipikae.chatop.dto.rental.NewRentalDTO;
import com.tipikae.chatop.dto.rental.RentalDTO;
import com.tipikae.chatop.dto.rental.UpdateRentalDTO;
import com.tipikae.chatop.exceptions.ConverterDTOException;
import com.tipikae.chatop.exceptions.rental.RentalNotFoundException;
import com.tipikae.chatop.exceptions.user.UserNotFoundException;
import com.tipikae.chatop.services.rental.IRentalService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rentals")
@Validated
public class RentalController {

    @Autowired
    private IRentalService rentalService;

    /**
     * Create a rental.
     * @param newRentalDTO NewRentalDTO object.
     * @return ResponseEntity<String>
     * @throws UserNotFoundException thrown when owner is not found.
     * @throws ConverterDTOException thrown when a converter exception occurred.
     */
    @PostMapping("")
    public ResponseEntity<String> createRental(@RequestBody @Valid NewRentalDTO newRentalDTO)
            throws UserNotFoundException, ConverterDTOException {
        String message = "Rental created !";
        rentalService.createRental(newRentalDTO);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    /**
     * Get all rentals.
     * @return ResponseEntity<List<RentalDTO>>
     * @throws ConverterDTOException thrown when a converter exception occurred.
     */
    @GetMapping("")
    public ResponseEntity<List<RentalDTO>> getAllRentals() throws ConverterDTOException {
        List<RentalDTO> rentals = rentalService.getAllRentals();
        return new ResponseEntity<>(rentals, HttpStatus.OK);
    }

    /**
     * Get a rental by id.
     * @param id Rental's id.
     * @return ResponseEntity<RentalDTO>
     * @throws RentalNotFoundException thrown when rental is not found.
     * @throws ConverterDTOException thrown when a converter exception occurred.
     */
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
     * @return ResponseEntity<String>
     * @throws UserNotFoundException thrown when owner is not found.
     * @throws RentalNotFoundException thrown when rental is not found.
     * @throws ConverterDTOException thrown when a converter exception occurred.
     */
    @PutMapping("/{id}")
    public ResponseEntity<String> updateRental(
            @PathVariable("id") @NotNull @Positive long id,
            @RequestBody @Valid UpdateRentalDTO updateRentalDTO )
            throws UserNotFoundException, RentalNotFoundException, ConverterDTOException {

        String message = "Rental updated !";
        rentalService.updateRental(id, updateRentalDTO);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
