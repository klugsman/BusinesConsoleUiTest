#@regression
#Feature: Request A single Card
#  As a client administrator
#  I want to request for a single card
#  So I can so I can manage my transactions
#
#  @wip
#  Scenario: Create a single card
#    Given I am on the Account Home Page
#    When I request for a single card
#    And I select the following options for the new card
#      | Option          | Specification |
#      | cardFactory     | single_card   |
#      | currency        | GBP           |
#      | amountMajorUnit | 10            |
#      | amountMinorUnit | 00            |
#      | fundingAccount  | FA_GDP        |
#    Then I should be able to create a new card with the details below:
#      | Option               | Details     |
#      | cardFactory          | single_card |
#      | balance on Creation  | GBP         |
#      | availableBalance     | 10          |
#      | actualBalance        | 00          |
#      | state                | Active      |
#      | sourceFundingAccount | FA_GDP      |