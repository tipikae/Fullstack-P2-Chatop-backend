package com.tipikae.chatop.repositories;

import com.tipikae.chatop.models.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Rental repository.
 */
public interface IRentalRepository extends JpaRepository<Rental, Long> { }
