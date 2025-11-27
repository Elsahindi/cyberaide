package fr.insatoulouse.studentsservice.repository;

import fr.insatoulouse.studentsservice.model.Availability;
import fr.insatoulouse.studentsservice.model.Skill;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IAvailabilityRepository extends JpaRepository<@NonNull Availability, @NonNull UUID> {
}
