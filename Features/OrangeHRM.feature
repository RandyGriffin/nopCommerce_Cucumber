Feature: OrangeHRM Login

  Scenario: Logo presence on OrangeHRM home page
    Given I launched chrome browser
    When I open orange hrm homepage
    Then I verify that the logo is present on page
    And close browser