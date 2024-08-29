package extentia.tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import extentia.TestComponents.BaseTest;
import extentia.pageObjects.CartPage;
import extentia.pageObjects.CheckoutPage;
import extentia.pageObjects.ConfirmationPage;
import extentia.pageObjects.ProductCatalogue;


public class ErrorValidationsTest extends BaseTest {
	
@Test (groups= {"ErrorHandling"},retryAnalyzer=extentia.TestComponents.Retry.class)
public void loginErrorValidation() throws IOException, InterruptedException
{
	
		String productName = "ZARA COAT 3";
		
		ProductCatalogue productcatalogue= landingpage.loginApplication("Faisal1@gmail.com", "Gmail123456tt@");
		Assert.assertEquals("Incorrect email  password.", landingpage.getErrorMessage());
        
	}
@Test
public void productErrorValidation() throws IOException, InterruptedException
{
	
		String productName = "ZARA COAT 3";
		
		ProductCatalogue productcatalogue= landingpage.loginApplication("Faisal3@gmail.com", "Faisal@123");
        List<WebElement> products =productcatalogue.getProductList();
		productcatalogue.addProductToCart(productName);
		
		
		CartPage cartpage=productcatalogue.goToCartPage();
		
		Boolean match=cartpage.VerifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);
		

	
	}
}
