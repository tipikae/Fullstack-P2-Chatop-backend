package com.tipikae.chatop.controllers;


import com.tipikae.chatop.dto.message.NewMessageDTO;
import com.tipikae.chatop.exceptions.ConverterDTOException;
import com.tipikae.chatop.exceptions.rental.RentalNotFoundException;
import com.tipikae.chatop.exceptions.user.UserNotFoundException;
import com.tipikae.chatop.models.Response;
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

    /**
     * Add a new message.
     * @param newMessageDTO NewMessageDTO object to add.
     * @return ResponseEntity<Response>
     * @throws UserNotFoundException thrown when a user is not found.
     * @throws RentalNotFoundException thrown whe a rental is not found.
     * @throws ConverterDTOException thrown when a converter exception occurred.
     */
    @PostMapping(name = "", consumes = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Response> addMessage(@RequestBody @Valid NewMessageDTO newMessageDTO)
            throws UserNotFoundException, RentalNotFoundException, ConverterDTOException {

        messageService.add(newMessageDTO);
        Response response = new Response("Message send with success");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
