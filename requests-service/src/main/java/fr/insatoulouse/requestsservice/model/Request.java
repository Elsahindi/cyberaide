package fr.insatoulouse.requestsservice.model;

import fr.insatoulouse.shared.enums.RequestStatus;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.UUID;

@Entity
@Data
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String keyword;

    @Column(nullable = false)
    private UUID requesterUuid;

    @Column(nullable = false)
    private Date dueDate;

    @Enumerated(EnumType.STRING)
    private RequestStatus status;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private Date createdAt;

}
