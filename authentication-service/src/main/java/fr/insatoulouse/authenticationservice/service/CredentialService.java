package fr.insatoulouse.authenticationservice.service;

import fr.insatoulouse.authenticationservice.model.Credential;
import fr.insatoulouse.authenticationservice.repository.CredentialRepository;
import fr.insatoulouse.shared.dto.CreateCredentialDTO;
import fr.insatoulouse.shared.dto.CreateStudentDTO;
import fr.insatoulouse.shared.dto.StudentDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CredentialService {

    private final CredentialRepository credentialRepository;

    private final StudentService studentService;

    public Credential getAndValidateCredential(String email, String password) {
        Credential credential = credentialRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));

        // TODO: Should never store passwords in plain text. Use hashing (e.g., BCrypt) instead.
        // This is just for demonstration purposes.
        if (!credential.getPassword().equals(password)) {
            throw new IllegalArgumentException("Invalid email or password");
        }

        return credential;
    }

    public void createCredential(CreateCredentialDTO credentialDTO, CreateStudentDTO studentDTO) {
        Credential credential = new Credential();
        credential.setEmail(credentialDTO.email());
        credential.setPassword(credentialDTO.password());
        credentialRepository.save(credential);

        studentService.createStudent(new StudentDTO(
                credential.getUuid(),
                studentDTO.firstName(),
                studentDTO.lastName(),
                studentDTO.school(),
                studentDTO.field()
        ));
    }

}
