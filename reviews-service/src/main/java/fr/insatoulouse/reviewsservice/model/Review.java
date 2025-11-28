package fr.insatoulouse.reviewsservice.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @Column(nullable = false)
    private UUID requestUUID;

    @Column(nullable = false)
    private UUID helperUUID;

    @Column(nullable = false)
    private UUID authorUUID;

    @Column(nullable = false)
    private int rating;

    @Column(nullable = false)
    private String comment;
}
