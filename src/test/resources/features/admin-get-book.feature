Feature: Get Books API

  Background:
    Given I am authenticated as "admin"

  Scenario: Get all books
    When I visit all book page
    Then I should see the list of books

  Scenario: Get existent book by ID
    When I request a book with ID "1"
    Then I should see the book information
