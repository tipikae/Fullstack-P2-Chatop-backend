package com.tipikae.chatop.it.repository;

import com.tipikae.chatop.models.User;
import com.tipikae.chatop.repositories.IUserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserRepositoryIT {

    @Autowired
    private IUserRepository userRepository;

    @Test
    void test() {
        String email = "test@test.com";
        String name = "name";
        String password = "password";
        LocalDateTime createdAt = LocalDateTime.now();

        User user = new User();
        user.setEmail(email);
        user.setName(name);
        user.setPassword(password);
        user.setCreatedAt(createdAt);

        // save
        User userSaved = userRepository.save(user);
        assertNotNull(userSaved);
        assertEquals(user.getName(), userSaved.getName());

        // get one by id
        assertTrue(userRepository.findById(userSaved.getId()).isPresent());
        assertEquals(1, userRepository.findByEmail(user.getEmail()).size());

        // get one by email
        assertTrue(userRepository.findFirstByEmail(user.getEmail()).isPresent());
        assertEquals(user.getEmail(), userRepository.findFirstByEmail(user.getEmail()).get().getEmail());
    }
}
