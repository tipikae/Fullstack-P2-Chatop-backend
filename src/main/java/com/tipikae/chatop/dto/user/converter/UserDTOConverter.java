package com.tipikae.chatop.dto.user.converter;

import com.tipikae.chatop.dto.user.NewUserDTO;
import com.tipikae.chatop.dto.user.UserDTO;
import com.tipikae.chatop.exceptions.ConverterDTOException;
import com.tipikae.chatop.models.User;
import org.springframework.stereotype.Component;

/**
 * User DTO converter.
 */
@Component
public class UserDTOConverter implements IUserDTOConverter {

    /**
     * Convert a NewUserDTO to a User.
     *
     * @param newUserDTO NewUserDTO object.
     * @return User
     * @throws ConverterDTOException Thrown when a converter error occurred.
     */
    @Override
    public User convertNewDTOToModel(NewUserDTO newUserDTO) throws ConverterDTOException {
        User user = new User();

        try {
            user.setEmail(newUserDTO.getEmail());
            user.setName(newUserDTO.getName());
            user.setPassword(newUserDTO.getPassword());
        } catch(Exception e) {
            throw new ConverterDTOException(e.getMessage());
        }

        return user;
    }

    /**
     * Convert a User to a UserDTO.
     *
     * @param user User object.
     * @return UserDTO
     * @throws ConverterDTOException Thrown when a converter error occurred.
     */
    @Override
    public UserDTO convertModelToDTO(User user) throws ConverterDTOException {
        UserDTO userDTO = new UserDTO();

        try {
            userDTO.setId(user.getId());
            userDTO.setEmail(user.getEmail());
            userDTO.setName(user.getName());
            userDTO.setCreatedAt(user.getCreatedAt());
            userDTO.setUpdatedAt(user.getUpdatedAt());
        } catch(Exception e) {
            throw new ConverterDTOException(e.getMessage());
        }

        return userDTO;
    }
}
