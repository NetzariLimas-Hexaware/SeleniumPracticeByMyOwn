package Hexaware.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Hexaware.AbstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent {
	private WebDriver driver;
	public ProductCatalogue(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".mb-3")
	private List<WebElement> products;
	
	@FindBy(css="button[routerlink*='cart']")
	private WebElement cartButton;
	
	@FindBy(css="button[routerlink*='myorders']")
	private WebElement ordersButton;
	
	@FindBy(id="toast-container")
	private WebElement toastContainer;
	
	@SuppressWarnings("unused")
	private By toastContainerBy = By.id("toastContainer");
	
	private By productsBy = By.cssSelector(".mb-3");
	
	public List<WebElement> getProductCatalogue() {
		waitElementToAppear(productsBy);
		return products;
	}
	
	public WebElement getSpecificProduct(String productName) {
		System.out.println(productName);
		return getProductCatalogue().stream().filter(s->s.findElement(By.tagName("b")).getText().equals(productName)).findAny().orElse(null);
	}
	
	public void addToCart(String productName) {
		getSpecificProduct(productName).findElement(By.cssSelector("button:last-of-type")).click();
	}
	
	public CheckOutPage goToCheckout(String productName) throws InterruptedException {
		addToCart(productName);
		Thread.sleep(2000);
//		waitElementToDisappear(toastContainerBy);
		cartButton.click();
		return new CheckOutPage(driver);
	}
	
	public OrderPage goToOrders() {
		ordersButton.click();
		return new OrderPage(driver);
	}

}
