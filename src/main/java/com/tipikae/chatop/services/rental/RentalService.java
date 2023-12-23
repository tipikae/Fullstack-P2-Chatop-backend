package com.tipikae.chatop.services.rental;

import com.tipikae.chatop.dto.rental.NewRentalDTO;
import com.tipikae.chatop.dto.rental.RentalDTO;
import com.tipikae.chatop.dto.rental.UpdateRentalDTO;
import com.tipikae.chatop.dto.rental.converter.IRentalDTOConverter;
import com.tipikae.chatop.exceptions.ConverterDTOException;
import com.tipikae.chatop.exceptions.rental.RentalNotFoundException;
import com.tipikae.chatop.exceptions.storage.StorageException;
import com.tipikae.chatop.exceptions.user.UserNotFoundException;
import com.tipikae.chatop.models.Rental;
import com.tipikae.chatop.models.User;
import com.tipikae.chatop.repositories.IRentalRepository;
import com.tipikae.chatop.repositories.IUserRepository;
import com.tipikae.chatop.services.storage.IStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Rental service.
 */
@Service
public class RentalService implements IRentalService {

    @Autowired
    private IRentalRepository rentalRepository;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IRentalDTOConverter rentalDTOConverter;

    @Autowired
    private IStorageService storageService;

    /**
     * Create a new rental.
     *
     * @param newRentalDTO NewRentalDTO to create.
     * @param ownerId Owner id.
     * @return RentalDTO
     * @throws ConverterDTOException thrown when a converter exception occurred.
     * @throws UserNotFoundException thrown when the owner is not found.
     * @throws StorageException thrown when a storage exception occurred.
     */
    @Override
    public RentalDTO createRental(NewRentalDTO newRentalDTO, long ownerId)
            throws ConverterDTOException, UserNotFoundException, StorageException {
        Optional<User> optionalUser = userRepository.findById(ownerId);
        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException(String.format("Owner with id=%d is not found.", ownerId));
        }

        return rentalDTOConverter.convertModelToRentalDTO(
                rentalRepository.save(
                        rentalDTOConverter.convertNewDTOToModel(
                                newRentalDTO, optionalUser.get(), storageService.store(newRentalDTO.getPicture()))));
    }

    /**
     * Get all rentals.
     *
     * @return List
     * @throws ConverterDTOException thrown when a converter exception occurred.
     */
    @Override
    public List<RentalDTO> getAllRentals() throws ConverterDTOException {
        return rentalDTOConverter.convertModelsToRentalDTOs(rentalRepository.findAll());
    }

    /**
     * Get a rental by id.
     *
     * @param id Rental id.
     * @return RentalDTO
     * @throws RentalNotFoundException thrown when rental is not found.
     * @throws ConverterDTOException   thrown when a converter exception occurred.
     */
    @Override
    public RentalDTO getRentalById(long id) throws RentalNotFoundException, ConverterDTOException {
        Optional<Rental> optionalRental = rentalRepository.findById(id);
        if (optionalRental.isEmpty()) {
            throw new RentalNotFoundException(String.format("Rental with id=%d is not found.", id));
        }

        return rentalDTOConverter.convertModelToRentalDTO(optionalRental.get());
    }

    /**
     * Update a rental.
     *
     * @param id              Rental id.
     * @param updateRentalDTO UpdateRentalDTO object.
     * @throws RentalNotFoundException thrown when rental is not found.
     * @throws ConverterDTOException   thrown when a converter exception occurred.
     * @throws UserNotFoundException thrown when the owner is not found.
     */
    @Override
    public void updateRental(long id, UpdateRentalDTO updateRentalDTO) throws RentalNotFoundException, ConverterDTOException, UserNotFoundException {
        Optional<Rental> optionalRental = rentalRepository.findById(id);
        if (optionalRental.isEmpty()) {
            throw new RentalNotFoundException(String.format("Rental with id=%d is not found.", id));
        }

        rentalRepository.save(rentalDTOConverter.convertUpdateDTOToModel(updateRentalDTO, optionalRental.get()));
    }
}
