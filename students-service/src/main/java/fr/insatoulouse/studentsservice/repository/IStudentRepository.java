package fr.insatoulouse.studentsservice.repository;

import fr.insatoulouse.studentsservice.model.Student;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IStudentRepository extends JpaRepository<@NonNull Student, @NonNull UUID> {
    List<Student> findDistinctBySkills_TitleIn(List<String> skillNames);
}
