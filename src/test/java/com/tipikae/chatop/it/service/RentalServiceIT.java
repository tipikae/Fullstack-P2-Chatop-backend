package com.tipikae.chatop.it.service;

import com.tipikae.chatop.dto.rental.NewRentalDTO;
import com.tipikae.chatop.dto.rental.RentalDTO;
import com.tipikae.chatop.dto.rental.UpdateRentalDTO;
import com.tipikae.chatop.dto.user.NewUserDTO;
import com.tipikae.chatop.dto.user.UserDTO;
import com.tipikae.chatop.exceptions.ConverterDTOException;
import com.tipikae.chatop.exceptions.rental.RentalNotFoundException;
import com.tipikae.chatop.exceptions.user.UserAlreadyExistsException;
import com.tipikae.chatop.exceptions.user.UserNotFoundException;
import com.tipikae.chatop.services.rental.IRentalService;
import com.tipikae.chatop.services.user.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class RentalServiceIT {

    @Autowired
    private IRentalService rentalService;

    @Autowired
    private IUserService userService;

    @Test
    void test() throws UserAlreadyExistsException, ConverterDTOException, UserNotFoundException, RentalNotFoundException {
        String name = "rental";
        int surface = 100;
        int price = 1000;
        String picture = "pic";
        String description = "desc";
        NewUserDTO newUserDTO = new NewUserDTO("test@test.com", "test", "1234");
        UserDTO owner = userService.createUser(newUserDTO);
        long owner_id = owner.getId();

        // create
        NewRentalDTO newRentalDTO = new NewRentalDTO(name, surface, price, picture, description, owner_id);
        RentalDTO rentalSaved = rentalService.createRental(newRentalDTO);
        assertEquals(newRentalDTO.getDescription(), rentalSaved.getDescription());

        // get all
        assertEquals(1, rentalService.getAllRentals().size());

        // get one by id
        assertEquals(owner.getId(), rentalService.getRentalById(rentalSaved.getId()).getOwnerId());
        assertThrows(RentalNotFoundException.class, () -> rentalService.getRentalById(1000));

        // update rental
        String updatedName = "updated";
        UpdateRentalDTO updatedRental = new UpdateRentalDTO(updatedName, surface, price, picture, description, owner_id);
        rentalService.updateRental(rentalSaved.getId(), updatedRental);
        assertEquals(updatedName, rentalService.getRentalById(rentalSaved.getId()).getName());
        assertThrows(RentalNotFoundException.class, () -> rentalService.updateRental(1000, updatedRental));
        updatedRental.setOwnerId(1000);
        assertThrows(UserNotFoundException.class, () -> rentalService.updateRental(rentalSaved.getId(), updatedRental));
    }
}
