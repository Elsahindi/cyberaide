package fr.insatoulouse.authenticationservice.service;

import fr.insatoulouse.shared.dto.CreateStudentDTO;
import fr.insatoulouse.shared.dto.StudentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class StudentService {
    public static final String AUTHENTICATION_SERVICE_URL = "http://students-service";

    @Autowired
    private RestTemplate restTemplate;

    public StudentDTO createStudent(StudentDTO dto) {
        return restTemplate.postForObject(
                AUTHENTICATION_SERVICE_URL + "/student",
                dto,
                StudentDTO.class
        );
    }

}
