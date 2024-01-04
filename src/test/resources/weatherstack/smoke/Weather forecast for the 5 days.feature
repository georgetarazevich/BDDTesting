@allure.label.layer:web
Feature: The Weather forecast for the 5 days
  As a user
  I want to know the Weather for some days
  To be ready for anything

  @SmokeTest @regress
  Scenario: A user can see the Weather for 5 days: To see if the project works
    Given The main page Weatherstack
    When A user open the main page Weatherstack
    Then A user see the Weather forecast for the 5 days

