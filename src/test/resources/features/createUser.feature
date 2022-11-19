Feature: Books Store API functionality

  @wip
  Scenario: Create a user
    When User sends a POST Request to create user end point
    And User captures status code userID username information
    Then Verify status code username and UserID is NOT null