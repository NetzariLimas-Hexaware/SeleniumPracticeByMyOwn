package Hexaware.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Hexaware.AbstractComponents.AbstractComponent;

public class CheckOutPage extends AbstractComponent {
	private WebDriver driver;
	public CheckOutPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".cart li")
	private List<WebElement> cartItems;
	
	@FindBy(css=".totalRow button")
	private WebElement checkOutButton;
	
	By cartItemsBy = By.cssSelector(".cart li");
	
	public List<WebElement> getCartItems() {
		waitElementToAppear(cartItemsBy);
		return cartItems;
	}
	
	public WebElement getSpecificItem(String productName) {
		return getCartItems().stream().filter(s->s.findElement(By.tagName("h3")).getText().equals(productName)).findAny().orElse(null);
	}
	
	public InformationDetailsPage goToInformationDetails(String productName) {
		getSpecificItem(productName);
		checkOutButton.click();
		return new InformationDetailsPage(driver);
	}

}
