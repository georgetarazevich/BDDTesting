#@allure.label.layer:rest
Feature: Common user API errors

  Background:
    Given access_key "<access_key>"

  @regress
  Scenario Outline: A user sends query with invalid access_key
    Given The user has query to send
    When The user sends query with invalid access key
    Then The user receive code 101 invalid_access_key


    Examples:
      | access_key |
      | 12345      |
      | 123456     |
      | 1234567    |

