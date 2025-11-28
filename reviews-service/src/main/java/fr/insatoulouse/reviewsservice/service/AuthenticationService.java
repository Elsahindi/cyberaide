package fr.insatoulouse.reviewsservice.service;

import fr.insatoulouse.shared.dto.SessionDTO;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthenticationService {
    public static final String AUTHENTICATION_SERVICE_URL = "http://authentication-service";

    @Autowired
    private RestTemplate restTemplate;

    @Nullable
    public SessionDTO getUserSession(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        System.out.println("Sent token: " + token);

        HttpEntity<Void> entity = new HttpEntity<>(headers);

        ResponseEntity<SessionDTO> response = restTemplate.exchange(
                AUTHENTICATION_SERVICE_URL + "/session",
                HttpMethod.GET,
                entity,
                SessionDTO.class
        );

        return response.getBody();
    }

    public boolean isUserAuthenticated(String token) {
        SessionDTO session = getUserSession(token);
        return session != null;
    }


}
