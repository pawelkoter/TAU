Feature: Register
  Scenario Outline: Successful registration
    Given A login page
    And User enters valid email
    And Clicks create account button
    And Enters first name <first_name>
    And Enters last name <last_name>
    And Enters password <password>
    And Enters address first name <address_first_name>
    And Enters address last name <address_last_name>
    And Enters street <street>
    And Enters city <city>
    And Selects state <state>
    And Enters postal code <postal_code>
    And Enters phone namber <phone_number>
    When Finally he clicks register button
    Then User account page shows up

    Examples:
      | first_name | last_name | password | address_first_name | address_last_name | street | city    | state   | postal_code | phone_number |
      | Jaś        | Fasola    | password | Jaś                | Fasola            | Błotna | Wąchock | Alabama | 12345       | 123456789    |
