@regression
Feature: Testing Login requirement feature
  As a client administrator
  I want to login to the application
  So I can manage a user account

  @regression @test
  Scenario: Login test of Business Console application
    Given I am on the login page
    When I login with client username & "client password"
    Then I should be able to see the text "login message"


#  @regression
#  Scenario: Login test of Business Console application
#    Given I am on the login page
#    When I login with client username & "client password"
#    Then lvdlgjls e the text "login message"
#
#  @regression
#  Scenario: Login test of Business Console application
#    Given I am on the login page
#    When I login with client username & "client password"
#    Then lvdlgjls e the text "login message"