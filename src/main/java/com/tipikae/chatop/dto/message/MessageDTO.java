package com.tipikae.chatop.dto.message;

import com.tipikae.chatop.models.Rental;
import com.tipikae.chatop.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Message DTO.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageDTO {

    private long id;
    private long rentalId;
    private long userId;
    private String message;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
