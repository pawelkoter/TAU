Feature: Login
  Scenario Outline: Valid credentials
    Given User is on login page
    And Uses <email> and <password>
    When He clicks submit button
    Then He is redirected to user account page

    Examples:
      | email         | password |
      | this@is.email | password  |

  Scenario Outline: Invalid credentials
    Given User is on login page
    And Uses <email> and <password>
    When He clicks submit button
    Then Authentication error is displayed

    Examples:
      | email         | password |
      | this@is.email | haslo    |
      | moj@mail.zk   | qwerty   |
