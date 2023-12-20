package com.tipikae.chatop.models;

import com.tipikae.chatop.dto.rental.RentalDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * Rentals response.
 */
@Data
@AllArgsConstructor
public class RentalsResponse {

    private List<RentalDTO> rentals;
}
