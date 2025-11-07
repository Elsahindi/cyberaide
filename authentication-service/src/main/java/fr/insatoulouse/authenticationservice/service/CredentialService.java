package fr.insatoulouse.authenticationservice.service;

import fr.insatoulouse.authenticationservice.dto.CreateCredentialDTO;
import fr.insatoulouse.authenticationservice.model.Credential;
import fr.insatoulouse.authenticationservice.repository.CredentialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CredentialService {

    private final CredentialRepository credentialRepository;

    public Credential getAndValidateCredential(String email, String password) {
        Credential credential = credentialRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));

        if (!credential.getPassword().equals(password)) {
            throw new IllegalArgumentException("Invalid email or password");
        }

        return credential;
    }

    public Credential createCredential(CreateCredentialDTO dto) {
        Credential credential = new Credential();
        credential.setEmail(dto.email());
        credential.setPassword(dto.password());
        return credentialRepository.save(credential);
    }

}
