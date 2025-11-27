package fr.insatoulouse.studentsservice.controller;

import fr.insatoulouse.shared.dto.CreateSkillDTO;
import fr.insatoulouse.shared.dto.SkillDTO;
import fr.insatoulouse.shared.dto.StudentDTO;
import fr.insatoulouse.studentsservice.model.Skill;
import fr.insatoulouse.studentsservice.model.Student;
import fr.insatoulouse.studentsservice.service.SkillService;
import fr.insatoulouse.studentsservice.service.StudentService;
import jakarta.ws.rs.core.MediaType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class SkillController {
    // POST - /student/:id/skills - Add a skill to a student
    // GET - /student/:id/skills - Get all skills of a student
    // DELETE - /student/:id/skills/:skillId - Remove a skill from a student
    private final SkillService skillService;
    private final StudentService studentService;

    @GetMapping(path = "/student/{id}/skill", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public ResponseEntity<List<SkillDTO>> show(@PathVariable UUID id) {
        Student student = studentService.getStudent(id);

        if (student == null) {
            return ResponseEntity.notFound().build();
        }

        List<Skill> skills = skillService.getSKillsByStudent(student);

        if (skills == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(skills.stream().map(skill -> new SkillDTO(
                skill.getUuid(),
                skill.getTitle(),
                new StudentDTO(
                        skill.getStudent().getUuid(),
                        skill.getStudent().getFirstName(),
                        skill.getStudent().getLastName(),
                        skill.getStudent().getSchool(),
                        skill.getStudent().getField(),
                        skill.getStudent().isHelper()
                )
        )).toList());
    }

    @PostMapping(path = "/student/{id}/skill", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public ResponseEntity<SkillDTO> create(@PathVariable UUID id, @RequestBody CreateSkillDTO dto, @RequestHeader("Authorization") String token) {
        Skill skill = skillService.addSkillToStudent(dto, id, token);

        if (skill == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(new SkillDTO(
                skill.getUuid(),
                skill.getTitle(),
                new StudentDTO(
                        skill.getStudent().getUuid(),
                        skill.getStudent().getFirstName(),
                        skill.getStudent().getLastName(),
                        skill.getStudent().getSchool(),
                        skill.getStudent().getField(),
                        skill.getStudent().isHelper()
                )
        ));
    }

    @DeleteMapping(path = "/student/{id}/skill/{skillId}", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public ResponseEntity<?> delete(@PathVariable UUID id, @PathVariable UUID skillId, @RequestHeader("Authorization") String token) {
        Skill skill = skillService.getSkill(skillId);

        if (skill == null) {
            return ResponseEntity.notFound().build();
        }

        if (!skill.getStudent().getUuid().equals(id)) {
            return ResponseEntity.badRequest().build();
        }

        this.skillService.removeSkillFromStudent(skill, skill.getStudent(), token);

        return ResponseEntity.noContent().build();
    }


}
