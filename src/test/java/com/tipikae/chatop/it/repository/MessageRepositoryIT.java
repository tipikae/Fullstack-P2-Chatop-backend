package com.tipikae.chatop.it.repository;

import com.tipikae.chatop.models.Message;
import com.tipikae.chatop.models.Rental;
import com.tipikae.chatop.models.User;
import com.tipikae.chatop.repositories.IMessageRepository;
import com.tipikae.chatop.repositories.IRentalRepository;
import com.tipikae.chatop.repositories.IUserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class MessageRepositoryIT {

    @Autowired
    private IMessageRepository messageRepository;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IRentalRepository rentalRepository;

    @Test
    void test() {
        String msg = "message";
        User owner = userRepository.save(new User(1, "email", "owner", "123456", LocalDateTime.now(), null));
        User sender = userRepository.save(new User(2, "email2", "sender", "123456", LocalDateTime.now(), null));
        Rental rental = rentalRepository.save(
                new Rental(1, "name", 100, 200, "picture", "description", owner, LocalDateTime.now(), null));

        Message message = new Message();
        message.setMessage(msg);
        message.setUser(sender);
        message.setRental(rental);
        message.setCreatedAt(LocalDateTime.now());

        // save
        Message messageSaved = messageRepository.save(message);
        assertNotNull(messageSaved);
        assertEquals(message.getMessage(), messageSaved.getMessage());
    }
}
