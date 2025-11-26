package fr.insatoulouse.authenticationservice.controller;

import fr.insatoulouse.authenticationservice.service.CredentialService;
import fr.insatoulouse.shared.dto.CreateCredentialDTO;
import fr.insatoulouse.shared.dto.CreateStudentDTO;
import fr.insatoulouse.shared.dto.PairDTO;
import jakarta.ws.rs.core.MediaType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/credential")
@RequiredArgsConstructor
public class CredentialController {

    private final CredentialService credentialService;

    @PostMapping(produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public ResponseEntity<Void> store(@RequestBody PairDTO<CreateCredentialDTO, CreateStudentDTO> dto) {
        credentialService.createCredential(dto.first(), dto.second());

        return ResponseEntity.created(
                ServletUriComponentsBuilder.fromPath("/session").build().toUri()
        ).build();
    }
}
