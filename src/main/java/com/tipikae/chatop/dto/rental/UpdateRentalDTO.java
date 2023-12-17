package com.tipikae.chatop.dto.rental;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Rental DTO update.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateRentalDTO {

    @NotBlank(message = "Name must not be blank.")
    @Size(max = 255, message = "Name length must be less than 255 characters.")
    private String name;

    @NotNull(message = "Surface is required.")
    private int surface;

    @NotNull(message = "Price is required.")
    private int price;

    @NotBlank(message = "Description must not be blank.")
    @Size(max = 2000, message = "Description length must be less than 2000 characters.")
    private String description;
}
