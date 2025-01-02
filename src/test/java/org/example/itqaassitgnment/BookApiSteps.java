package org.example.itqaassitgnment;

import io.cucumber.java.en.Given;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
public class BookApiSteps {

    private static final Logger logger = LoggerFactory.getLogger(BookApiSteps.class);

    private final String backendServerUrl = "http://localhost:7081";

    private final RestTemplate restTemplate;
    private ResponseEntity<String> response;
    private String bookId;
    private String role;

    public BookApiSteps() {
        this.restTemplate = new RestTemplate();
    }

    // Authentication
    @Given("I am authenticated as {string}")
    public void i_am_authenticated_as(String role) {
        logger.info("Authenticating as role: {}", role);
        this.role = role; // Store the role for later use
    }

    private HttpHeaders createAuthHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(role, "password");
        return headers;
    }
}