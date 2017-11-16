Feature: Search By Regex
  Scenario: Match year in title
    Given Following books in repository
      |Title           |
      |Metro 2033      |
      |Witajcie w Rosji|
      |Apokalypsis '89 |
      |Haszyszopenki   |
    And Pattern Matching years
    When I search book by this pattern
    Then Books with following titles are found
      |Metro 2033      |
      |Apokalypsis '89 |
