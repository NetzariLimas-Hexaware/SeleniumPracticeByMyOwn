package Hexaware.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import Hexaware.AbstractComponents.AbstractComponent;

public class OrderPage extends AbstractComponent {
	@SuppressWarnings("unused")
	private WebDriver driver;
	public OrderPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver = driver;
	}
	
	@FindBy(css="tr td:nth-child(3)")
	private List<WebElement> productNames;
		
	public boolean VerifyOrderDisplay(String productName) {
		return productNames.stream().anyMatch(s->s.getText().equalsIgnoreCase(productName));
	}

}
