package fr.insatoulouse.authenticationservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class StudentService {
    public static final String AUTHENTICATION_SERVICE_URL = "http://authentication-service";

    @Autowired
    private RestTemplate restTemplate;

}
