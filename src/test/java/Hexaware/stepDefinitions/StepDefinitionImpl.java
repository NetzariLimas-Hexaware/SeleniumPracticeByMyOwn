package Hexaware.stepDefinitions;

import org.testng.Assert;

import Hexaware.TestComponents.BaseTest;
import Hexaware.pageobjects.CheckOutPage;
import Hexaware.pageobjects.ConfirmationPage;
import Hexaware.pageobjects.InformationDetailsPage;
import Hexaware.pageobjects.LoginPage;
import Hexaware.pageobjects.ProductCatalogue;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionImpl extends BaseTest {
	private LoginPage loginPage;
	private ProductCatalogue productCatalogue;
	private CheckOutPage checkoutPage;
	private InformationDetailsPage informationDetails;
	private ConfirmationPage confirmationPage;
	
	@Given("^I landed on Ecommerce Page$")
    public void i_landed_on_ecommerce_page() throws Throwable {
        loginPage = launchApplication();
    }

    @Given("^Logged in with email (.+) and password (.+)$")
    public void logged_in_with_email_and_password(String email, String password) throws Throwable {
    	productCatalogue = loginPage.login(email, password);
    }

    @When("^I add product (.+) from Cart$")
    public void i_add_product_from_cart(String product) throws Throwable {
    	checkoutPage = productCatalogue.goToCheckout(product);
    }

    @And("^Checkout (.+) and verify information$")
    public void checkout_and_verify_information(String product) throws Throwable {
    	informationDetails = checkoutPage.goToInformationDetails(product);
    }
    
    @Then("^Submit the order$")
    public void submit_the_order() throws Throwable {
    	confirmationPage = informationDetails.placeOrder("Mexico");
    }


    @And("^\"([^\"]*)\" message is displayed on ConfirmationPage$")
    public void something_message_is_displayed_on_confirmationpage(String strArg1) throws Throwable {
    	Assert.assertEquals(strArg1, confirmationPage.confirmConfirmationMessage(strArg1));
    	driver.close();
    }
}
