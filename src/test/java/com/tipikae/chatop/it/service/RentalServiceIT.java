package com.tipikae.chatop.it.service;

import com.tipikae.chatop.dto.rental.NewRentalDTO;
import com.tipikae.chatop.dto.rental.RentalDTO;
import com.tipikae.chatop.dto.rental.UpdateRentalDTO;
import com.tipikae.chatop.dto.user.NewUserDTO;
import com.tipikae.chatop.dto.user.UserDTO;
import com.tipikae.chatop.exceptions.ConverterDTOException;
import com.tipikae.chatop.exceptions.rental.RentalNotFoundException;
import com.tipikae.chatop.exceptions.storage.StorageException;
import com.tipikae.chatop.exceptions.user.UserAlreadyExistsException;
import com.tipikae.chatop.exceptions.user.UserNotFoundException;
import com.tipikae.chatop.services.rental.IRentalService;
import com.tipikae.chatop.services.user.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class RentalServiceIT {

    @Autowired
    private IRentalService rentalService;

    @Autowired
    private IUserService userService;

    @Test
    void test() throws UserAlreadyExistsException, ConverterDTOException, UserNotFoundException, RentalNotFoundException, StorageException {
        String name = "rental";
        int surface = 100;
        int price = 1000;
        MultipartFile picture = new MockMultipartFile("file", "hello.txt", MediaType.TEXT_PLAIN_VALUE, "Hello World!".getBytes());
        String description = "desc";
        NewUserDTO newUserDTO = new NewUserDTO("test5@test.com", "test", "1234");
        UserDTO owner = userService.createUser(newUserDTO);
        long owner_id = owner.getId();

        // create
        NewRentalDTO newRentalDTO = new NewRentalDTO(name, surface, price, picture, description);
        RentalDTO rentalSaved = rentalService.createRental(newRentalDTO, owner_id);
        assertEquals(newRentalDTO.getDescription(), rentalSaved.getDescription());

        // get all
        assertTrue(rentalService.getAllRentals().size() > 0);

        // get one by id
        assertEquals(owner.getId(), rentalService.getRentalById(rentalSaved.getId()).getOwnerId());
        assertThrows(RentalNotFoundException.class, () -> rentalService.getRentalById(1000));

        // update rental
        String updatedName = "updated";
        UpdateRentalDTO updatedRental = new UpdateRentalDTO(updatedName, surface, price, description);
        rentalService.updateRental(rentalSaved.getId(), updatedRental);
        assertEquals(updatedName, rentalService.getRentalById(rentalSaved.getId()).getName());
        assertThrows(RentalNotFoundException.class, () -> rentalService.updateRental(1000, updatedRental));
    }
}
