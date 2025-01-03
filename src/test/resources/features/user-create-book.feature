Feature: Create Book API

  Background:
    Given I am authenticated as "user"

  Scenario: Create a new book successfully
    When I create a new book with following details:
      | title | author |
      | test title 10 | test author 10 |
    Then the book should be created successfully
    #And I should see the new book in the list

  Scenario: Create book with missing fields
    When I create a new book with following details:
      | title | author |
      |       |        |
    Then I should see a 400 error message2
