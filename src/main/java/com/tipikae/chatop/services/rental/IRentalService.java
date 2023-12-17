package com.tipikae.chatop.services.rental;

import com.tipikae.chatop.dto.rental.NewRentalDTO;
import com.tipikae.chatop.dto.rental.RentalDTO;
import com.tipikae.chatop.dto.rental.UpdateRentalDTO;
import com.tipikae.chatop.exceptions.ConverterDTOException;
import com.tipikae.chatop.exceptions.rental.RentalNotFoundException;
import com.tipikae.chatop.exceptions.storage.StorageException;
import com.tipikae.chatop.exceptions.user.UserNotFoundException;

import java.util.List;

/**
 * Rental service interface.
 */
public interface IRentalService {

    /**
     * Create a new rental.
     * @param newRentalDTO NewRentalDTO to create.
     * @param ownerId Owner id.
     * @return RentalDTO
     * @throws ConverterDTOException thrown when a converter exception occurred.
     * @throws UserNotFoundException thrown when the owner is not found.
     * @throws StorageException thrown when a storage exception occurred.
     */
    RentalDTO createRental(NewRentalDTO newRentalDTO, long ownerId) throws ConverterDTOException, UserNotFoundException, StorageException;

    /**
     * Get all rentals.
     * @return List<RentalDTO>
     * @throws ConverterDTOException thrown when a converter exception occurred.
     */
    List<RentalDTO> getAllRentals() throws ConverterDTOException;

    /**
     * Get a rental by id.
     * @param id Rental id.
     * @return RentalDTO
     * @throws RentalNotFoundException thrown when rental is not found.
     * @throws ConverterDTOException thrown when a converter exception occurred.
     */
    RentalDTO getRentalById(long id) throws RentalNotFoundException, ConverterDTOException;

    /**
     * Update a rental.
     * @param id Rental id.
     * @param updateRentalDTO UpdateRentalDTO object.
     * @throws RentalNotFoundException thrown when rental is not found.
     * @throws ConverterDTOException thrown when a converter exception occurred.
     * @throws UserNotFoundException thrown when the owner is not found.
     */
    void updateRental(long id, UpdateRentalDTO updateRentalDTO) throws RentalNotFoundException, ConverterDTOException, UserNotFoundException;
}
