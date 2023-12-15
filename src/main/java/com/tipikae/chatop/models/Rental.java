package com.tipikae.chatop.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Rental model.
 */
@Entity
@Table(name = "RENTALS")
@Data@NoArgsConstructor
@AllArgsConstructor
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

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

    @ManyToOne()
    @JoinColumn(name = "owner_id", nullable = false)
    private User user;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
