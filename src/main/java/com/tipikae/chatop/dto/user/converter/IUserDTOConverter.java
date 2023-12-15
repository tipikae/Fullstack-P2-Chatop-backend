package com.tipikae.chatop.dto.user.converter;

import com.tipikae.chatop.dto.user.NewUserDTO;
import com.tipikae.chatop.dto.user.UserDTO;
import com.tipikae.chatop.exceptions.ConverterDTOException;
import com.tipikae.chatop.models.User;

/**
 * User DTO converter interface.
 */
public interface IUserDTOConverter {

    /**
     * Convert a NewUserDTO to a User.
     * @param dto NewUserDTO object.
     * @return User
     * @throws ConverterDTOException Thrown when a converter error occurred.
     */
    User convertNewDTOToModel(NewUserDTO dto) throws ConverterDTOException;

    /**
     * Convert a User to a UserDTO.
     * @param user User object.
     * @return UserDTO
     * @throws ConverterDTOException Thrown when a converter error occurred.
     */
    UserDTO convertModelToDTO(User user) throws ConverterDTOException;
}
