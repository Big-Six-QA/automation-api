package org.example.itqaassitgnment.steps;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.APIResponse;
import io.cucumber.java.en.Then;
import org.example.itqaassitgnment.model.Book;
import org.example.itqaassitgnment.util.ResponseManager;
import org.junit.jupiter.api.Assertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import java.util.Map;

public class BookVerificationSteps extends BaseSteps {
    private static final Logger logger = LoggerFactory.getLogger(BookVerificationSteps.class);

    @Then("I should see a {int} error message")
    public void i_should_see_a_error_message(int statusCode) {
        APIResponse response = ResponseManager.getInstance().getResponse();
        assert response != null : "Response is null";
        assert response.status() == statusCode :
                "Expected status " + statusCode + ", but got " + response.status();
    }

    @Then("I should see the book information")
    public void i_should_see_the_book_information() {
        APIResponse response = ResponseManager.getInstance().getResponse();
        assert response.ok() : "Expected successful response, but got " + response.status();
        try {
            Book book = bookApiService.extractBookFromResponse(response);
            logger.info("Retrieved book details: {}", book);
        } catch (Exception e) {
            logger.error("Error processing book details: {}", e.getMessage());
            throw new AssertionError("Failed to process book details: " + e.getMessage());
        }
    }

    @Then("The book should be deleted successfully")
    public void the_book_should_be_deleted_successfully() {
        APIResponse response = ResponseManager.getInstance().getResponse();
        assert response.ok() : "Expected successful response, but got " + response.status();
    }

    @Then("I should see a {int} Already Reported message")
    public void i_should_see_a_already_reported_message(int statusCode) {
        logger.info("Verifying response with status code {}", statusCode);
        APIResponse response = ResponseManager.getInstance().getResponse();
        Assertions.assertEquals(statusCode, response.status());
    }

    @Then("I should see the updated book details")
    public void i_should_see_the_updated_book_details() {
        APIResponse response = ResponseManager.getInstance().getResponse();
        logger.info("Verifying the updated book details");
        Assertions.assertEquals(HttpStatus.OK.value(), response.status());

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> updatedBookDetails = objectMapper.readValue(response.text(), new TypeReference<Map<String, Object>>() {});
            if (updatedBookDetails.isEmpty()) {
                logger.info("No updated book details found.");
                return;
            }
            logger.info("Updated book details: {}", updatedBookDetails);
        } catch (JsonProcessingException e) {
            logger.error("Error processing JSON response: {}", e.getMessage());
            Assertions.fail("Failed to parse the response body as JSON");
        }
    }

}
