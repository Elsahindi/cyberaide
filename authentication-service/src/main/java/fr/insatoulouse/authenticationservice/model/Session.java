package fr.insatoulouse.authenticationservice.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;
import java.util.UUID;

@Data
@Entity
@Table(name = "sessions")
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false)
    private UUID uuid;

    @Column(nullable = false)
    private UUID credentialUuid;

    @Column(nullable = false, unique = true)
    private String token;

    @Column(nullable = false)
    @CreatedDate
    private Date createdAt;

    @Column(nullable = false)
    private Date expiresAt;
}
