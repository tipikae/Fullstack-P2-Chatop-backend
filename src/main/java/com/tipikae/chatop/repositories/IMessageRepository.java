package com.tipikae.chatop.repositories;

import com.tipikae.chatop.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Message repository.
 */
@Repository
public interface IMessageRepository extends JpaRepository<Message, Long> {
}
