@tag
Feature: Purchase the order from ecommerce website

	Background:
	Given I landed on Ecommerce Page

  @Regression
  Scenario Outline: Positive test of submitting the order
    Given Logged in with email <email> and password <password>
    When I add product <product> from Cart
    And Checkout <product> and verify information
    Then Submit the order
    And "THANKYOU FOR THE ORDER." message is displayed on ConfirmationPage

    Examples: 
      | email  										| password 		| product  	 					|
      | netzari_limas@hotmail.com | Angela13 		| ZARA COAT 3 				|
      | netzari_limas@hotmail.com | Angela13    | ADIDAS ORIGINAL     |
