package com.tipikae.chatop.it.service;

import com.tipikae.chatop.dto.message.MessageDTO;
import com.tipikae.chatop.dto.message.NewMessageDTO;
import com.tipikae.chatop.dto.rental.NewRentalDTO;
import com.tipikae.chatop.dto.rental.RentalDTO;
import com.tipikae.chatop.dto.user.NewUserDTO;
import com.tipikae.chatop.dto.user.UserDTO;
import com.tipikae.chatop.exceptions.ConverterDTOException;
import com.tipikae.chatop.exceptions.rental.RentalNotFoundException;
import com.tipikae.chatop.exceptions.storage.StorageException;
import com.tipikae.chatop.exceptions.user.UserAlreadyExistsException;
import com.tipikae.chatop.exceptions.user.UserNotFoundException;
import com.tipikae.chatop.services.message.IMessageService;
import com.tipikae.chatop.services.rental.IRentalService;
import com.tipikae.chatop.services.user.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class MessageServiceIT {

    @Autowired
    private IMessageService messageService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IRentalService rentalService;

    @Test
    void test() throws UserAlreadyExistsException, ConverterDTOException, UserNotFoundException, StorageException, RentalNotFoundException {
        String msg = "message";
        UserDTO owner = userService.createUser(new NewUserDTO("test@test.com", "test", "123456"));
        UserDTO sender = userService.createUser(new NewUserDTO("test@test2.com", "test2", "123456"));
        MockMultipartFile picture = new MockMultipartFile("name", "test".getBytes());
        RentalDTO rental = rentalService.createRental(new NewRentalDTO("name", 100, 200, picture, "description"), owner.getId());

        NewMessageDTO newMessageDTO = new NewMessageDTO();
        newMessageDTO.setMessage(msg);
        newMessageDTO.setUserId(sender.getId());
        newMessageDTO.setRentalId(rental.getId());

        // save
        MessageDTO messageSaved = messageService.add(newMessageDTO);
        assertNotNull(messageSaved);
        assertEquals(newMessageDTO.getRentalId(), messageSaved.getRentalId());
    }
}
