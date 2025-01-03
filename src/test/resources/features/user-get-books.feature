Feature: Get Books API

  Background:
    Given I am authenticated as "user"

  Scenario: Get non-existent book
    When I request a book with ID "999"
    Then I should see a 404 error message

  Scenario: Get book with invalid ID format
    When I request a book with ID "abc"
    Then I should see a 400 error message