package com.tipikae.chatop.dto.rental.converter;

import com.tipikae.chatop.dto.rental.NewRentalDTO;
import com.tipikae.chatop.dto.rental.RentalDTO;
import com.tipikae.chatop.dto.rental.UpdateRentalDTO;
import com.tipikae.chatop.exceptions.ConverterDTOException;
import com.tipikae.chatop.models.Rental;
import com.tipikae.chatop.models.User;

import java.util.List;

/**
 * Rental DTO interface.
 */
public interface IRentalDTOConverter {

    /**
     * Convert a NewRentalDTO to a Rental model.
     * @param newRentalDTO NewRentalDTO object.
     * @param owner User owner.
     * @param pictureUrl Picture url String.
     * @return Rental
     * @throws ConverterDTOException thrown when a converter exception occurred.
     */
    Rental convertNewDTOToModel(NewRentalDTO newRentalDTO, User owner, String pictureUrl) throws ConverterDTOException;

    /**
     * Convert an UpdateRentalDTO to a Rental model.
     * @param updateRentalDTO UpdateRentalDTO object.
     * @param rental Rental model to update.
     * @return Rental
     * @throws ConverterDTOException thrown when a converter exception occurred.
     */
    Rental convertUpdateDTOToModel(UpdateRentalDTO updateRentalDTO, Rental rental) throws ConverterDTOException;

    /**
     * Convert a Rental model to a RentalDTO.
     * @param rental Rental model.
     * @return RentalDTO
     * @throws ConverterDTOException thrown when a converter exception occurred.
     */
    RentalDTO convertModelToRentalDTO(Rental rental) throws ConverterDTOException;

    /**
     * Convert a list of Rental model to a list of RentalDTO.
     * @param rentals List of Rental model.
     * @return List
     * @throws ConverterDTOException thrown when a converter exception occurred.
     */
    List<RentalDTO> convertModelsToRentalDTOs(List<Rental> rentals) throws ConverterDTOException;
}
