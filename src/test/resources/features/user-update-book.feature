Feature: Update Book API

  Background:
    Given I am authenticated as "user"

  Scenario: Update an existing book
    When I update the book with following details:
      | id | title | author |
      | 3 | update title | update author |
    Then I should see a 403 error message

  Scenario: Update non-existing book
    When I update the book with following details:
      | id | title | author |
      | 999 | update title | update author |
    Then I should see a 403 error message

  Scenario: Update an existing book with missing title
    When I update the book with following details:
      | id | title | author |
      | 3 |  | update author 1 |
    Then I should see a 403 error message