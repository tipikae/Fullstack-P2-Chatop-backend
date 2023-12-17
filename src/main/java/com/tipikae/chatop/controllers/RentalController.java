package com.tipikae.chatop.controllers;

import com.tipikae.chatop.dto.rental.NewRentalDTO;
import com.tipikae.chatop.dto.rental.RentalDTO;
import com.tipikae.chatop.dto.rental.UpdateRentalDTO;
import com.tipikae.chatop.exceptions.ConverterDTOException;
import com.tipikae.chatop.exceptions.rental.RentalNotFoundException;
import com.tipikae.chatop.exceptions.storage.FileNotFoundException;
import com.tipikae.chatop.exceptions.storage.StorageException;
import com.tipikae.chatop.exceptions.user.UserNotFoundException;
import com.tipikae.chatop.services.rental.IRentalService;
import com.tipikae.chatop.services.storage.IStorageService;
import com.tipikae.chatop.services.user.IUserService;
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
     * @return ResponseEntity<String>
     * @throws UserNotFoundException thrown when owner is not found.
     * @throws ConverterDTOException thrown when a converter exception occurred.
     */
    @PostMapping( value = "", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE } )
    public ResponseEntity<String> createRental(
            @ModelAttribute @Valid NewRentalDTO newRentalDTO,
            Principal principal)
                throws UserNotFoundException, ConverterDTOException, StorageException {

        long ownerId = userService.getUserByEmail(principal.getName()).getId();
        rentalService.createRental(newRentalDTO, ownerId);
        String message = "Rental created !";
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
    @PutMapping( value = "/{id}", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE } )
    public ResponseEntity<String> updateRental(
            @PathVariable("id") @NotNull @Positive long id,
            @ModelAttribute @Valid UpdateRentalDTO updateRentalDTO )
                throws UserNotFoundException, RentalNotFoundException, ConverterDTOException {

        rentalService.updateRental(id, updateRentalDTO);
        String message = "Rental updated !";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    /**
     * Download a picture.
     * @param filename File name.
     * @return ResponseEntity<Resource>
     * @throws FileNotFoundException thrown when file is not found.
     * @throws StorageException thrown when a storage exception occurred.
     */
    @GetMapping("/uploads/{filename}")
    public ResponseEntity<Resource> loadPicture(@PathVariable("filename") @NotBlank String filename)
            throws FileNotFoundException, StorageException {
        Resource resource = storageService.load(filename);
        return new ResponseEntity<>(resource, HttpStatus.OK);
    }
}
