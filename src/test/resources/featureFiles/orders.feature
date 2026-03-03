@front-end
Feature: Validate ordering

  Background: Open browser and navigate to login page
    Given User is on login page
    When The user enter a valid credentials
    And The user clicks on login button

  Scenario: Place item in the shopping cart
    And clicks button to add item in cart
    Then should be visible counter red badge on cart icon

  Scenario: Place multiple items in the shopping cart
    And In inventpory page select 1 items
    And User clicks on shoping cart icon
    Then There should be 1 items in the shopping cart list

  Scenario Outline: Validate tax and total
    And User selects
      | Sauce Labs Backpack     |
      | Sauce Labs Bike Light   |
      | Sauce Labs Bolt T-Shirt |
    And User clicks on shoping cart icon
    And Clicks Checkout button
    And User enter "<firstName>", "<lastName>" and "<zipCode>"
    And Clicks Continue button
    Then Tax should be "Tax: $4.48"
    And Total should be "Total: $60.45"
    Examples:
      | firstName | lastName | zipCode |
      | Petar     | Petrovic | 21000   |
