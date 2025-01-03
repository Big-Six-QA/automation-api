Feature: Create Book API

  Background:
    Given I am authenticated as "admin"

  Scenario: Create a new book successfully
    When I create a new book with following details:
      | id | title     | author      |
      |1 | test title | test author |
    Then the book should be created successfully

  Scenario: Create book with missing fields
    When I create a new book with following details:
      | title | author |
      |       |        |
    Then I should see a 400 error message
