
@tag
Feature: Error Validation
  I want to use this template for my feature file
  @ErrorValidation
  Scenario Outline: Title of your scenario outline
    Given I landed on ecommerce page
    When I logged in with username <name> and password <password>
    Then "Incorrect email or password." is displayed.

    Examples: 
      | name  		 | password  | 
      | sd@abc.com |  Abc@134 | 