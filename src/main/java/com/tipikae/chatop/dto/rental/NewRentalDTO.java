package com.tipikae.chatop.dto.rental;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * New Rental DTO.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewRentalDTO {

    @NotBlank(message = "Name must not be blank.")
    private String name;

    @NotNull(message = "Surface is required.")
    private int surface;

    @NotNull(message = "Price is required.")
    private int price;

    @NotBlank(message = "Picture must not be blank.")
    private String picture;

    @NotBlank(message = "Description must not be blank.")
    private String description;

    @NotNull(message = "Owner is required.")
    private long ownerId;
}
