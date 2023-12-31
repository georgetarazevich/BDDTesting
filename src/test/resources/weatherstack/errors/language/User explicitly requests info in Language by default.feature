#@allure.label.layer:rest
Feature: Common user API errors

  @regress
  Scenario Outline: A user explicitly requests info in Language by default
    Given The user has query to send  with Language "<Language>"
    When The User explicitly requests info in Language by default
    Then The user receive code 605 invalid_language

    Examples:
      | Language |
      | en       |

