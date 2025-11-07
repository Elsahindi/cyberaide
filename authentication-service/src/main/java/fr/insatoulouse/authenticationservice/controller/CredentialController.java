package fr.insatoulouse.authenticationservice.controller;

import fr.insatoulouse.authenticationservice.dto.CreateCredentialDTO;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/credential")
@RequiredArgsConstructor
public class CredentialController {



    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void store(@RequestBody CreateCredentialDTO dto) {


    }

}
