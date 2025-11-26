package fr.insatoulouse.studentsservice.repository;

import fr.insatoulouse.studentsservice.model.Student;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface StudentRepository extends JpaRepository<@NonNull Student, @NonNull UUID> {

}
