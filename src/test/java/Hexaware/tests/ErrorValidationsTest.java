package Hexaware.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import Hexaware.TestComponents.BaseTest;
import Hexaware.TestComponents.Retry;
import Hexaware.pageobjects.ProductCatalogue;

public class ErrorValidationsTest extends BaseTest {
	@Test(groups= {"Error Handling"}, retryAnalyzer=Retry.class)
	public void LoginErrorValidation() throws IOException, InterruptedException {
		
		loginPage.login("netzari_limas@hotmail.com", "Angela111231213");
		Assert.assertEquals("Incorrect email or password.", loginPage.getErrorMessage());
	}
}
