package fr.insatoulouse.requestsservice.service;

import fr.insatoulouse.shared.dto.SessionDTO;
import fr.insatoulouse.shared.dto.StudentDTO;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class StudentService {
    public static final String AUTHENTICATION_SERVICE_URL = "http://students-service";

    @Autowired
    private RestTemplate restTemplate;

    @Nullable
    public List<StudentDTO> getStudentsWithSkills(List<String> skills) {
        ResponseEntity<List<StudentDTO>> response = restTemplate.exchange(
                AUTHENTICATION_SERVICE_URL + "/student?skills=" + String.join(",", skills),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<StudentDTO>>() {
                }
        );

        return response.getBody();
    }


}
