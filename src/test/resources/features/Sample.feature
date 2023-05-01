@sample
Feature: Smoke Test Cases

  @ApplicationLogin
  Scenario: Application login
    Given user launch the application URL
    Then user should be taken to post login page
