package fr.insatoulouse.studentsservice.service;

import fr.insatoulouse.shared.dto.CreateSkillDTO;
import fr.insatoulouse.shared.dto.SessionDTO;
import fr.insatoulouse.shared.dto.SkillDTO;
import fr.insatoulouse.shared.dto.StudentDTO;
import fr.insatoulouse.studentsservice.model.Skill;
import fr.insatoulouse.studentsservice.model.Student;
import fr.insatoulouse.studentsservice.repository.ISkillRepository;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SkillService {

    @Autowired
    private ISkillRepository skillRepository;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private StudentService studentService;

    @Nullable
    public Skill getSkill(UUID id) {
        return skillRepository.findById(id).orElse(null);
    }

    @Nullable
    public List<Skill> getSKillsByStudent(Student student) {
        // TODO: peut être ça marche pas (LAZY LOADING)
        return student.getSkills();
    }

    @Nullable
    public Skill addSkillToStudent(CreateSkillDTO skillDto, UUID studentId, String token) {
        SessionDTO session = authenticationService.getUserSession(token);

        if (session == null || !session.credentialUuid().equals(studentId)) {
            throw new IllegalArgumentException("Unauthorized");
        }

        Student student = this.studentService.getStudent(studentId);

        Skill skillToAdd = new Skill();
        skillToAdd.setTitle(skillDto.title());
        skillToAdd.setStudent(student);

        return skillRepository.save(skillToAdd);
    }

    public void removeSkillFromStudent(Skill skill, Student student, String token) {
        SessionDTO session = authenticationService.getUserSession(token);

        if (session == null || !session.credentialUuid().equals(student.getUuid())) {
            throw new IllegalArgumentException("Unauthorized");
        }

        skillRepository.delete(skill);
    }
}
