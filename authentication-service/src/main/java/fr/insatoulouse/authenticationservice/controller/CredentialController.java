package fr.insatoulouse.authenticationservice.controller;

import fr.insatoulouse.authenticationservice.dto.CreateCredentialDTO;
import fr.insatoulouse.authenticationservice.service.CredentialService;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/credential")
@RequiredArgsConstructor
public class CredentialController {

    private final CredentialService credentialService;

    @PostMapping(produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public ResponseEntity<Void> store(@RequestBody CreateCredentialDTO dto) {
        credentialService.createCredential(dto);

        return ResponseEntity.created(
                ServletUriComponentsBuilder.fromPath("/session").build().toUri()
        ).build();
    }
}
