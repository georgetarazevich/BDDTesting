#@allure.label.layer:rest
Feature: Common user API errors

  Background:
    Given access_key "key_123"

  @regress
  Scenario: A user sends query with invalid access_key
    Given The user has query to send
    When The user sends query with invalid access key
    Then The user receive code 101 invalid_access_key



