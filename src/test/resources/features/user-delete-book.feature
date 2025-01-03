Feature: Delete Book API

  Background:
    Given I am authenticated as "user"

  Scenario: Delete an existing book
    When I delete the book with ID "1"
    Then I should see a 403 error message

  Scenario: Delete non-existent book
    When I delete the book with ID "999"
    Then I should see a 403 error message