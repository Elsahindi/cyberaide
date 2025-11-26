package fr.insatoulouse.studentsservice.model;

import fr.insatoulouse.shared.enums.Field;
import fr.insatoulouse.shared.enums.School;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Enumerated(EnumType.STRING)
    private School school;

    @Enumerated(EnumType.STRING)
    private Field field;
}
