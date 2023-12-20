package com.tipikae.chatop.repositories;

import com.tipikae.chatop.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * User repository.
 */
@Repository
public interface IUserRepository extends JpaRepository<User, Long> {

    /**
     * Find users by their email.
     * @param email String User's email.
     * @return List
     */
    List<User> findByEmail(String email);

    /**
     * Find a user by its email.
     * @param email USer's email.
     * @return Optional
     */
    Optional<User> findFirstByEmail(String email);
}
