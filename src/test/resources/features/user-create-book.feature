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
    Then I should see a 400 error message

    #user-create-book.feature
  Scenario: Create a book with missing title
    When I create a new book with following details:
      | title | author |
      |       | test author 5 |
    Then I should see a 400 error message

  Scenario: Create a book with missing author
    When I create a new book with following details:
      | title | author |
      | test title 36 |        |
    Then I should see a 400 error message

  Scenario: Create a new book with existent values
    When I create a new book with following details:
      | title | author |
      | title 1 | author 1 |
    Then I should see a 208 Already Reported message
