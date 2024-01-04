@allure.label.layer:rest
Feature: Common user API errors

  @regress
  Scenario Outline: A User requests info with not existing city name: "тестовые задания_QA", Негативный тест
    Given The user has query to send
    When The user requests info with city name "<city>"
    Then The user receive code "<ErrorCode>"

    Examples:
      | city         | ErrorCode |
      | MinskaVolost | 615       |
