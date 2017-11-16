Feature: Delete By List
  Scenario: Delete multiple books
    Given There are following books in repository
      |Title           |
      |Metro 2033      |
      |Witajcie w Rosji|
      |Apokalypsis '89 |
      |Haszyszopenki   |
    When I delete
      |Title           |
      |Metro 2033      |
      |Haszyszopenki   |
    Then Only books with following titles are left
      |Witajcie w Rosji|
      |Apokalypsis '89 |
