package com.tipikae.chatop.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * User DTO.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private long id;
    private String email;
    private String name;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
