package com.tipikae.chatop.controllers;


import com.tipikae.chatop.dto.message.NewMessageDTO;
import com.tipikae.chatop.exceptions.ConverterDTOException;
import com.tipikae.chatop.exceptions.rental.RentalNotFoundException;
import com.tipikae.chatop.exceptions.user.UserNotFoundException;
import com.tipikae.chatop.services.message.IMessageService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Message controller
 */
@RestController
@RequestMapping("/messages")
@Validated
public class MessageController {

    @Autowired
    private IMessageService messageService;

    @PostMapping(name = "", consumes = { "application/json" })
    public ResponseEntity<String> addMessage(@RequestBody @Valid NewMessageDTO newMessageDTO)
            throws UserNotFoundException, RentalNotFoundException, ConverterDTOException {
        messageService.add(newMessageDTO);
        String message = "Message send with success";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
