package com.tipikae.chatop.dto.rental.converter;

import com.tipikae.chatop.dto.rental.NewRentalDTO;
import com.tipikae.chatop.dto.rental.RentalDTO;
import com.tipikae.chatop.dto.rental.UpdateRentalDTO;
import com.tipikae.chatop.exceptions.ConverterDTOException;
import com.tipikae.chatop.models.Rental;
import com.tipikae.chatop.models.User;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Rental DTO converter.
 */
@Component
public class RentalDTOConverter implements IRentalDTOConverter{

    /**
     * Convert a NewRentalDTO to a Rental model.
     *
     * @param newRentalDTO NewRentalDTO object.
     * @param owner User owner.
     * @return Rental
     * @throws ConverterDTOException thrown when a converter exception occurred.
     */
    @Override
    public Rental convertNewDTOToModel(NewRentalDTO newRentalDTO, User owner) throws ConverterDTOException {
        Rental rental = new Rental();

        try {
            rental.setName(newRentalDTO.getName());
            rental.setSurface(newRentalDTO.getSurface());
            rental.setPrice(newRentalDTO.getPrice());
            rental.setPicture(newRentalDTO.getPicture());
            rental.setDescription(newRentalDTO.getDescription());
            rental.setUser(owner);
            rental.setCreatedAt(LocalDateTime.now());
        } catch (Exception e) {
            throw new ConverterDTOException(e.getMessage());
        }

        return rental;
    }

    /**
     * Convert an UpdateRentalDTO to a Rental model.
     *
     * @param updateRentalDTO UpdateRentalDTO object.
     * @param rental          Rental model to update.
     * @param owner            User owner.
     * @return Rental
     * @throws ConverterDTOException thrown when a converter exception occurred.
     */
    @Override
    public Rental convertUpdateDTOToModel(UpdateRentalDTO updateRentalDTO, Rental rental, User owner) throws ConverterDTOException {
        try {
            rental.setName(updateRentalDTO.getName());
            rental.setSurface(updateRentalDTO.getSurface());
            rental.setPrice(updateRentalDTO.getPrice());
            rental.setPicture(updateRentalDTO.getPicture());
            rental.setDescription(updateRentalDTO.getDescription());
            rental.setUser(owner);
            rental.setUpdatedAt(LocalDateTime.now());
        } catch (Exception e) {
            throw new ConverterDTOException(e.getMessage());
        }
        return rental;
    }

    /**
     * Convert a Rental model to a RentalDTO.
     *
     * @param rental Rental model.
     * @return RentalDTO
     * @throws ConverterDTOException thrown when a converter exception occurred.
     */
    @Override
    public RentalDTO convertModelToRentalDTO(Rental rental) throws ConverterDTOException {
        RentalDTO rentalDTO = new RentalDTO();

        try {
            rentalDTO.setId(rental.getId());
            rentalDTO.setName(rental.getName());
            rentalDTO.setSurface(rental.getSurface());
            rentalDTO.setPrice(rental.getPrice());
            rentalDTO.setPicture(rental.getPicture());
            rentalDTO.setDescription(rental.getDescription());
            rentalDTO.setOwnerId(rental.getUser().getId());
            rentalDTO.setCreatedAt(rental.getCreatedAt());
            rentalDTO.setUpdatedAt(rental.getUpdatedAt());
        } catch (Exception e) {
            throw new ConverterDTOException(e.getMessage());
        }

        return rentalDTO;
    }

    /**
     * Convert a list of Rental model to a list of RentalDTO.
     *
     * @param rentals List of Rental model.
     * @return List<RentalDTO>
     * @throws ConverterDTOException thrown when a converter exception occurred.
     */
    @Override
    public List<RentalDTO> convertModelsToRentalDTOs(List<Rental> rentals) throws ConverterDTOException {
        List<RentalDTO> rentalDTOS = new ArrayList<>();

        try {
            rentalDTOS = rentals.stream().map(rental -> {
                try {
                    return convertModelToRentalDTO(rental);
                } catch (ConverterDTOException e) {
                    throw new RuntimeException(e);
                }
            }).collect(Collectors.toList());
        } catch (Exception e) {
            throw new ConverterDTOException(e.getMessage());
        }

        return rentalDTOS;
    }
}
