package com.tipikae.chatop.services.user;

import com.tipikae.chatop.dto.user.NewUserDTO;
import com.tipikae.chatop.dto.user.UserDTO;
import com.tipikae.chatop.dto.user.converter.IUserDTOConverter;
import com.tipikae.chatop.exceptions.ConverterDTOException;
import com.tipikae.chatop.exceptions.user.UserAlreadyExistsException;
import com.tipikae.chatop.exceptions.user.UserNotFoundException;
import com.tipikae.chatop.models.User;
import com.tipikae.chatop.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * User service.
 */
@Service
public class UserService implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IUserDTOConverter dtoConverter;

    /**
     * Create a user.
     *
     * @param newUserDTO NewUSerDTO to create.
     * @return UserDTO
     * @throws UserAlreadyExistsException thrown when a user already exists.
     */
    @Override
    public UserDTO createUser(NewUserDTO newUserDTO) throws UserAlreadyExistsException, ConverterDTOException {
        List<User> users = userRepository.findByEmail(newUserDTO.getEmail());
        if (!users.isEmpty()) {
            throw new UserAlreadyExistsException(String.format("User with email=%s already exists.", newUserDTO.getEmail()));
        }

        User user = dtoConverter.convertNewDTOToModel(newUserDTO);

        return dtoConverter.convertModelToDTO(userRepository.save(user));
    }

    /**
     * Get a user by its id.
     *
     * @param id User's id.
     * @return UserDTO
     * @throws UserNotFoundException thrown when a user is not found.
     * @throws ConverterDTOException thrown when a converter error occurred.
     */
    @Override
    public UserDTO getUserById(long id) throws UserNotFoundException, ConverterDTOException {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException(String.format("User with id=%d is not found.", id));
        }

        return dtoConverter.convertModelToDTO(optionalUser.get());
    }

    /**
     * Get a user by its email.
     *
     * @param email User's email.
     * @return UserDTO
     * @throws UserNotFoundException thrown when a user is not found.
     * @throws ConverterDTOException thrown when a converter error occurred.
     */
    @Override
    public UserDTO getUserByEmail(String email) throws UserNotFoundException, ConverterDTOException {
        Optional<User> optionalUser = userRepository.findFirstByEmail(email);
        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException(String.format("User with email=%s is not found.", email));
        }

        return dtoConverter.convertModelToDTO(optionalUser.get());
    }
}
