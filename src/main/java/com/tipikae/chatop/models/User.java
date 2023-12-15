package com.tipikae.chatop.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * User model.
 */
@Entity
@Table(
        name = "USERS",
        indexes = @Index(name = "USERS_index", columnList = "email", unique = true)
)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Email(regexp = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$", message = "Email must have an email format.")
    @NotBlank(message = "Email must not be blank.")
    private String email;

    @Size(min = 3, message = "Name length must be more than 3 characters.")
    @NotBlank(message = "Name must not be blank.")
    private String name;

    @Size(min = 3, message = "Password length must be more than 3 characters.")
    @NotBlank(message = "Password must not be blank.")
    private String password;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
