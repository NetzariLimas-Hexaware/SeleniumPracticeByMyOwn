package Hexaware.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Hexaware.TestComponents.BaseTest;
import Hexaware.pageobjects.CheckOutPage;
import Hexaware.pageobjects.ConfirmationPage;
import Hexaware.pageobjects.InformationDetailsPage;
import Hexaware.pageobjects.OrderPage;
import Hexaware.pageobjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest {
	String productName = "ZARA COAT 3";
	
	@Test(dataProvider= "getData", groups= {"Regression"})
	public void submitOrder(HashMap<String,String> input) throws InterruptedException {
		String country = "Mexico";
		String message = "THANKYOU FOR THE ORDER.";
		
		ProductCatalogue productCatalogue = loginPage.login(input.get("email"), input.get("password"));
		CheckOutPage checkoutPage = productCatalogue.goToCheckout(input.get("product"));
		InformationDetailsPage informationDetails = checkoutPage.goToInformationDetails(input.get("product"));
		ConfirmationPage confirmationPage = informationDetails.placeOrder(country);
		
		Assert.assertEquals(message, confirmationPage.confirmConfirmationMessage(message));
	}
	
	@Test(dependsOnMethods= { "submitOrder" })
	public void OrderHistoryTest() {
		ProductCatalogue productCatalogue = loginPage.login("netzari_limas@hotmail.com", "Angela13");
		OrderPage ordersPage = productCatalogue.goToOrders();
		Assert.assertTrue(ordersPage.VerifyOrderDisplay(productName));
	}
	
	@DataProvider
	public Object[][] getData() throws IOException{
		
		List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir") + "//src//test//java//Hexaware//data//Purchase.json");
		
		return new Object[][] { {data.get(0)}, {data.get(1)} };
	}
}
