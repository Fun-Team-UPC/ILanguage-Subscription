Feature: show subscription details
  Scenario: client sees subscription details
    Given the client is on the main page
    When the client searches for a subscription
    Then the client receives subscription  details