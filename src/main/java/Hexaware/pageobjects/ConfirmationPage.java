package Hexaware.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Hexaware.AbstractComponents.AbstractComponent;

public class ConfirmationPage extends AbstractComponent {
	@SuppressWarnings("unused")
	private WebDriver driver;
	public ConfirmationPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		this.driver = driver;
		
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="[class='hero-primary']")
	WebElement confirmationMessage;

	By confirmationMessageBy = By.cssSelector("[class='hero-primary']");
	
	public String confirmConfirmationMessage(String message) {
		waitElementToAppear(confirmationMessageBy);
		return confirmationMessage.getText();
	}
}
