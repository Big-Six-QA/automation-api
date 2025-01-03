Feature: Delete Book API

  Background:
    Given I am authenticated as "admin"

  Scenario: Delete an existing book
    When I delete the book with ID "1"
    Then The book should be deleted successfully

  Scenario: Delete non-existent book
    When I delete the book with ID "999"
    Then I should see a 404 error message

  Scenario: Delete a book with invalid ID format
    When I delete the book with ID "abc"
    Then I should see a 400 error message