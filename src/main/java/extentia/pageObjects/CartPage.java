package extentia.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import extentia.AbstractComponents.AbstractComponents;

public class CartPage extends AbstractComponents {
	
	WebDriver driver;
	public CartPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	
	}
	

	@FindBy(css=".cartSection h3")
	List<WebElement> cartProducts;
	
   @FindBy(css=".totalRow button")
   WebElement checkoutEle;
	

	
	public Boolean VerifyProductDisplay(String productName)

	{
		Boolean match = cartProducts.stream().anyMatch(product->product.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	public CheckoutPage goToCheckout()
	{
		
		checkoutEle.click();
		return new CheckoutPage(driver);
		 
		
		
		
	}
	

	

}
