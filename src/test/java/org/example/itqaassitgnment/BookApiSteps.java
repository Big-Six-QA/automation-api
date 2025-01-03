package org.example.itqaassitgnment;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
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

    // Get Book By ID
    @When("I request a book with ID {string}")
    public void i_request_a_book_with_id(String id) {
        logger.info("Requesting book with ID {} as {}", id, role);
        String url = backendServerUrl + "/api/books/" + id;
        HttpHeaders headers = createAuthHeaders();
        try {
            response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(headers), String.class);
        } catch (HttpClientErrorException e) {
            logger.error("Error requesting book with ID {}: {}", id, e.getMessage());
            response = new ResponseEntity<>(e.getStatusCode());
        }
    }

    @Then("I should see a {int} error message")
    public void i_should_see_a_error_message(int statusCode) {
        logger.info("Verifying error message with status code {}", statusCode);
        Assertions.assertEquals(statusCode, response. getStatusCode().value());
    }
}