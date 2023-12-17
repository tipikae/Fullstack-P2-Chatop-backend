package com.tipikae.chatop.dto.message.converter;

import com.tipikae.chatop.dto.message.MessageDTO;
import com.tipikae.chatop.dto.message.NewMessageDTO;
import com.tipikae.chatop.exceptions.ConverterDTOException;
import com.tipikae.chatop.models.Message;
import com.tipikae.chatop.models.Rental;
import com.tipikae.chatop.models.User;
import org.springframework.stereotype.Component;

/**
 * Message converter DTO.
 */
@Component
public class MessageConverterDTO implements IMessageConverterDTO {

    /**
     * Convert a NewMessageDTO to a Message.
     *
     * @param newMessageDTO NewMessageDTO object.
     * @param sender        User sender.
     * @param rental        Rental about.
     * @return Message
     * @throws ConverterDTOException thrown when a converter exception occurred.
     */
    @Override
    public Message convertNewMessageDTOTOModel(NewMessageDTO newMessageDTO, User sender, Rental rental) throws ConverterDTOException {
        return null;
    }

    /**
     * Convert a Message to a MessageDTO.
     *
     * @param message Message object.
     * @return MessageDTO
     * @throws ConverterDTOException thrown when a converter exception occurred.
     */
    @Override
    public MessageDTO convertMessageToDTO(Message message) throws ConverterDTOException {
        return null;
    }
}
