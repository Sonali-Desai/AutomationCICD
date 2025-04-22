
@tag
Feature: Purchase the dorder from Ecommerce Website
  I want to use this template for my feature file
 Background:
 Given I landed on ecommerce page
  @Submit
  Scenario Outline: Positive Test of submitting the order
    Given I logged in with username <name> and password <password>
    When I add product <productName> to cart
    And Checkout <productName> and submit the order
    Then "Thankyou for the order." message is displayed on confirmation page

    Examples: 
      | name  		 | password  | productName |
      | sd@abc.com |  Abc@1234 | ZARA COAT 3 |
    #  | ss@abc.com |	Abc@1234 |
