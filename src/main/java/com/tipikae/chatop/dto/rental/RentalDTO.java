package com.tipikae.chatop.dto.rental;

import com.tipikae.chatop.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Rental DTO.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RentalDTO {
    private long id;

    private String name;

    private int surface;

    private int price;

    private String picture;

    private String description;

    private long ownerId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
