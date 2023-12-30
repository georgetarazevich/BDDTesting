#@allure.label.layer:rest
Feature: Common user API errors

  @regress
  Scenario Outline: A User requests info with not existing city name
    Given The user has query to send
    When The User requests info with city name "<city>"
    Then The user receive code 615 request_failed

    Examples:
      | city         |
      | MinskaVolost |
