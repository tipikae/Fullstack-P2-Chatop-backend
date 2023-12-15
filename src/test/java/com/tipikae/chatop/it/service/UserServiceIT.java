package com.tipikae.chatop.it.service;

import com.tipikae.chatop.dto.user.NewUserDTO;
import com.tipikae.chatop.dto.user.UserDTO;
import com.tipikae.chatop.exceptions.ConverterDTOException;
import com.tipikae.chatop.exceptions.user.UserAlreadyExistsException;
import com.tipikae.chatop.exceptions.user.UserNotFoundException;
import com.tipikae.chatop.services.user.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class UserServiceIT {

    @Autowired
    private IUserService userService;

    @Test
    void test() throws UserAlreadyExistsException, ConverterDTOException, UserNotFoundException {
        String email = "test@test.com";
        String name = "name";
        String password = "password";

        // create user
        NewUserDTO newUserDTO = new NewUserDTO(email, name, password);
        UserDTO userSaved = userService.createUser(newUserDTO);
        assertEquals(newUserDTO.getName(), userSaved.getName());

        NewUserDTO sameEmail = new NewUserDTO(email, "test", "test");
        assertThrows(UserAlreadyExistsException.class, () -> userService.createUser(sameEmail));

        // get user by id
        assertEquals(newUserDTO.getEmail(), userService.getUserById(userSaved.getId()).getEmail());
        assertThrows(UserNotFoundException.class, () -> userService.getUserById(1000));

        // get user by email
        assertEquals(newUserDTO.getName(), userService.getUserByEmail(newUserDTO.getEmail()).getName());
        assertThrows(UserNotFoundException.class, () -> userService.getUserByEmail("not-found@not-found.com"));
    }
}
