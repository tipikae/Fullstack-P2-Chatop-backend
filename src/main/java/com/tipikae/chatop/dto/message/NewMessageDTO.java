package com.tipikae.chatop.dto.message;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * New Message DTO.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewMessageDTO {

    @NotBlank(message = "Message must not be blank.")
    @Size(max = 2000, message = "Message length must be less than 2000 characters.")
    private String message;

    @NotNull(message = "Sender is required.")
    private long user_id;

    @NotNull(message = "Rental is required.")
    private long rental_id;
}
