#@allure.label.layer:rest
Feature: Common user API errors

  @regress
  Scenario Outline: A user requests info in Language different than default
    Given The user has query to send  with Language "<Language>"
    When The user requests info in Language different than default
    Then The user receive code 105 function_access_restricted

    Examples:
      | Language |
      | de       |

