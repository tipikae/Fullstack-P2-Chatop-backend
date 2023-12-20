package com.tipikae.chatop.it.repository;

import com.tipikae.chatop.models.Rental;
import com.tipikae.chatop.models.User;
import com.tipikae.chatop.repositories.IRentalRepository;
import com.tipikae.chatop.repositories.IUserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class RentalRepositoryIT {

    @Autowired
    private IRentalRepository rentalRepository;

    @Autowired
    private IUserRepository userRepository;

    @Test
    void test() {
        String name = "rental";
        int surface = 100;
        int price = 1000;
        String picture = "pic";
        String description = "desc";
        User user = new User(1, "test2@test.com", "user", "123456", LocalDateTime.now(), null);
        User userSaved = userRepository.save(user);

        Rental rental = new Rental();
        rental.setName(name);
        rental.setSurface(surface);
        rental.setPrice(price);
        rental.setPicture(picture);
        rental.setDescription(description);
        rental.setUser(userSaved);


        // save
        Rental rentalSaved = rentalRepository.save(rental);
        assertNotNull(rentalSaved);
        assertEquals(rental.getName(), rentalSaved.getName());

        // get all
        assertTrue(rentalRepository.findAll().size() > 0);

        // get one by id
        assertEquals(rentalSaved.getDescription(), rentalRepository.findById(rentalSaved.getId()).get().getDescription());

        // update
        String updatedName = "updated";
        rentalSaved.setName(updatedName);
        rentalRepository.save(rentalSaved);
        assertTrue(rentalRepository.findAll().size() > 0);
        assertEquals(updatedName, rentalRepository.findById(rentalSaved.getId()).get().getName());
    }
}
