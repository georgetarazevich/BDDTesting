#@allure.label.layer:rest
Feature: The Weather forecast for some cities
  As a user
  I want to know the Weather forecast for some places
  To be ready for anything

  @regress
  Scenario Outline: A user can see current Temperature for the city
    Given Current Weatherstack information for the city "<city>"
    When A user parsing Weatherstack information
    Then A user see current Temperature info
    And The current Temperature is Less Then "<temperature>" C

    Examples:
      | city        | temperature |
      | Minsk       | 25          |
      | Oslo        | 20          |
      | London      | 15          |
      | Riga        | 10          |
      | Novosibirsk | 5           |

