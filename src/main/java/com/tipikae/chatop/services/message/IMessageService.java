package com.tipikae.chatop.services.message;

import com.tipikae.chatop.dto.message.MessageDTO;
import com.tipikae.chatop.dto.message.NewMessageDTO;
import com.tipikae.chatop.exceptions.ConverterDTOException;
import com.tipikae.chatop.exceptions.rental.RentalNotFoundException;
import com.tipikae.chatop.exceptions.user.UserNotFoundException;

/**
 * Message service interface.
 */
public interface IMessageService {

    /**
     * Add a new message.
     * @param newMessageDTO NewMessageDTO object to add.
     * @return MessageDTO
     * @throws ConverterDTOException thrown when a converter exception occurred.
     * @throws UserNotFoundException thrown when sender is not found.
     * @throws RentalNotFoundException thrown when rental is not found.
     */
    MessageDTO add(NewMessageDTO newMessageDTO) throws ConverterDTOException, UserNotFoundException, RentalNotFoundException;
}
