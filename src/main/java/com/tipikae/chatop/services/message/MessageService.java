package com.tipikae.chatop.services.message;

import com.tipikae.chatop.dto.message.MessageDTO;
import com.tipikae.chatop.dto.message.NewMessageDTO;
import com.tipikae.chatop.dto.message.converter.IMessageConverterDTO;
import com.tipikae.chatop.exceptions.ConverterDTOException;
import com.tipikae.chatop.exceptions.rental.RentalNotFoundException;
import com.tipikae.chatop.exceptions.user.UserNotFoundException;
import com.tipikae.chatop.models.Message;
import com.tipikae.chatop.models.Rental;
import com.tipikae.chatop.models.User;
import com.tipikae.chatop.repositories.IMessageRepository;
import com.tipikae.chatop.repositories.IRentalRepository;
import com.tipikae.chatop.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Message service.
 */
@Service
public class MessageService implements IMessageService {

    @Autowired
    private IMessageRepository messageRepository;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IRentalRepository rentalRepository;

    @Autowired
    private IMessageConverterDTO messageConverterDTO;

    /**
     * Add a new message.
     *
     * @param newMessageDTO NewMessageDTO object to add.
     * @return MessageDTO
     * @throws ConverterDTOException thrown when a converter exception occurred.
     * @throws UserNotFoundException thrown when sender is not found.
     * @throws RentalNotFoundException thrown when rental is not found.
     */
    @Override
    public MessageDTO add(NewMessageDTO newMessageDTO) throws ConverterDTOException, UserNotFoundException, RentalNotFoundException {
        Optional<User> optionalUser = userRepository.findById(newMessageDTO.getUser_id());
        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException(String.format("Sender with id=%d is not found.", newMessageDTO.getUser_id()));
        }

        Optional<Rental> optionalRental = rentalRepository.findById(newMessageDTO.getRental_id());
        if (optionalRental.isEmpty()) {
            throw new RentalNotFoundException(String.format("Rental with id=%d is not found.", newMessageDTO.getRental_id()));
        }

        return messageConverterDTO.convertMessageToDTO(
                messageRepository.save(
                        messageConverterDTO.convertNewMessageDTOTOModel(newMessageDTO, optionalUser.get(), optionalRental.get())));
    }
}
