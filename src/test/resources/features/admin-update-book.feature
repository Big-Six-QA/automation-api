Feature: Update Book API

  Background:
    Given I am authenticated as "admin"

  Scenario: Update an existing book
    When I update the book with following details:
      | id | title | author |
      | 1 | new title | new author |
    Then the book should be updated successfully
    And I should see the updated book details

  Scenario: Update an existing book with empty title
    When I update the book with following details:
      | id | title | author |
      | 1 | | new author |
    Then I should see a 400 error message