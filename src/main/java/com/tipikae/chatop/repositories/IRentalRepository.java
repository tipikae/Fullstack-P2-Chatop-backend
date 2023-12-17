package com.tipikae.chatop.repositories;

import com.tipikae.chatop.models.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Rental repository.
 */
@Repository
public interface IRentalRepository extends JpaRepository<Rental, Long> { }
