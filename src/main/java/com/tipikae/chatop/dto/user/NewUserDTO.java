package com.tipikae.chatop.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * New user DTO.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewUserDTO {

    private String email;
    private String name;
    private String password;
}
