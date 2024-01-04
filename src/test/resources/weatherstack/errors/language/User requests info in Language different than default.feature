@allure.label.layer:rest
Feature: Common user API errors

  @regress
  Scenario Outline: A user requests info in Language different than default: "тестовые задания_QA", Негативный тест
    Given The user has query to send  with Language "<Language>"
    When The user requests info in Language
    Then The user receive code "<ErrorCode>"

    Examples:
      | Language | ErrorCode |
      | de       | 105       |

