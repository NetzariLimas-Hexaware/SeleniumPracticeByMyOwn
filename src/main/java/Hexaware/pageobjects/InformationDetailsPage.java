package Hexaware.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Hexaware.AbstractComponents.AbstractComponent;

public class InformationDetailsPage extends AbstractComponent {
	private WebDriver driver;
	public InformationDetailsPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="div[class='user__address'] input")
	private WebElement countryInput;
	
	@FindBy(css="div[class='user__address'] button")
	private List<WebElement> countryElementsList;
	
	@FindBy(css="div[class='actions'] a")
	private WebElement placeOrderButton;
	
	By countryElementListBy = By.cssSelector("div[class='user__address'] button"); // one button
	By countryInputBy = By.cssSelector("div[class='user__address'] input"); // one input
	
	public List<WebElement> getCountryElementLists() {
		waitElementToAppear(countryElementListBy);
		return countryElementsList;
	}
	
	public WebElement getSpecificCountry(String country) {
		countryInput.sendKeys(country);
		return getCountryElementLists().stream().filter(s->s.getText().equals(country)).findAny().orElse(null);
	}
	
	public ConfirmationPage placeOrder(String country) {
		waitElementToAppear(countryInputBy);
		getSpecificCountry(country).click();
		placeOrderButton.click();
		return new ConfirmationPage(driver);
	}

}
