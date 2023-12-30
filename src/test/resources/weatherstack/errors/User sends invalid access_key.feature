#@allure.label.layer:rest
Feature: Common user API errors

  Scenario: A user sends invalid access_key
    Given The user has query to send
    When The user sends query with invalid access_key
    Then The user receive code 101
