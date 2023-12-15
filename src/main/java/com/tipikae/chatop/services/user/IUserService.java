package com.tipikae.chatop.services.user;

import com.tipikae.chatop.dto.user.NewUserDTO;
import com.tipikae.chatop.dto.user.UserDTO;
import com.tipikae.chatop.exceptions.ConverterDTOException;
import com.tipikae.chatop.exceptions.user.UserAlreadyExistsException;
import com.tipikae.chatop.exceptions.user.UserNotFoundException;

/**
 * User service interface.
 */
public interface IUserService {

    /**
     * Create a user.
     * @param newUserDTO NewUSerDTO to create.
     * @return UserDTO
     * @throws UserAlreadyExistsException thrown when a user already exists.
     * @throws ConverterDTOException thrown when a converter error occurred.
     */
    UserDTO createUser(NewUserDTO newUserDTO) throws UserAlreadyExistsException, ConverterDTOException;

    /**
     * Get a user by its id.
     * @param id User's id.
     * @return UserDTO
     * @throws UserNotFoundException thrown when a user is not found.
     * @throws ConverterDTOException thrown when a converter error occurred.
     */
    UserDTO getUserById(long id) throws UserNotFoundException, ConverterDTOException;
}
