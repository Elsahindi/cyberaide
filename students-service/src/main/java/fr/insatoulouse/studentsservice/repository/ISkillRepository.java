package fr.insatoulouse.studentsservice.repository;

import fr.insatoulouse.studentsservice.model.Skill;
import fr.insatoulouse.studentsservice.model.Student;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ISkillRepository extends JpaRepository<@NonNull Skill, @NonNull UUID> {
}
